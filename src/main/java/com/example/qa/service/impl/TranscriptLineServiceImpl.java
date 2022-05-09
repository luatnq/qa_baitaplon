package com.example.qa.service.impl;

import com.example.qa.converter.MappingHelper;
import com.example.qa.data.entity.*;
import com.example.qa.dto.*;
import com.example.qa.exception.BadRequestAlertException;
import com.example.qa.repository.*;
import com.example.qa.service.TranscriptHisService;
import com.example.qa.service.TranscriptLineService;
import com.example.qa.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TranscriptLineServiceImpl implements TranscriptLineService {

    final private MappingHelper mappingHelper;
    private final TranscriptLineRepository transcriptLineRepository;
    private final TranscriptItemRepository transcriptItemRepository;
    private final SubjectPointRepository subjectPointRepository;
    private final TranscriptHisService transcriptHisService;
    private final LogRepository logRepository;
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;

    private final String UPDATE_ACTION = "update";
    private final String CREATE_ACTION = "create";
    private final String DELETE_ACTION = "delete";
    private final String POINT_FINAL_DESCRIPTION = "final";

    @Override
    public TranscriptOverview getTranscript(int studyClassId, int subjectId) {

        List<TranscriptLine> transcriptLines = transcriptLineRepository.findByStudyClassId(studyClassId);
        List<TranscriptLineDTO> transcriptLineDTOs = mappingHelper.mapList(transcriptLines, TranscriptLineDTO.class);
        this.sortTranscriptItems(transcriptLineDTOs);

        List<Integer> transcriptLineIds = transcriptLineRepository.getIdsByStudyClassId(studyClassId);
        List<SubjectPoint> subjectPoints = subjectPointRepository.getSubjectPoint(subjectId);
        List<SubjectPointDTO> subjectPointDTOs = mappingHelper.mapList(subjectPoints, SubjectPointDTO.class);
        this.handlePointFinal(transcriptLineDTOs, subjectPointDTOs);

        List<Integer> lineIdsPreStatistic = this.getTranLineIdPass(transcriptLineDTOs);
        int countStudentFailFinalPointPre = transcriptLineIds.size() - lineIdsPreStatistic.size();
        int countStudentFailFinalPoint = this.statisticFailFinalPoint(lineIdsPreStatistic);
        int countStudentNonEligible = this.statisticStudentNonEligible(transcriptLineIds, transcriptLineDTOs);

        return new TranscriptOverview(handleOutputTranscriptLine(transcriptLineDTOs), subjectPointDTOs,
                transcriptLineDTOs.size() - countStudentFailFinalPoint - countStudentFailFinalPointPre,
                countStudentFailFinalPoint + countStudentFailFinalPointPre,
                transcriptLineDTOs.size() - countStudentNonEligible, countStudentNonEligible);
    }

    @Transactional
    public List<TranscriptLineDTO> updatePoint(int studyClassId, List<TranscriptLineDTO> updatedTranscriptLines, String username) {
        List<TranscriptLine> transcriptLines = transcriptLineRepository.findByStudyClassId(studyClassId);
        int updateLine = 0;
        User user = userRepository.findByUsername(username);
        LogHistory log = new LogHistory(UPDATE_ACTION, user);
        logRepository.save(log);
        for (TranscriptLine line : transcriptLines) {
            this.updateTranscriptItem(updatedTranscriptLines.get(updateLine).getTranscriptItems(), line);
            transcriptHisService.createTranscriptHis(line, username, UPDATE_ACTION, log);
            updateLine++;
        }
        return mappingHelper.mapList(transcriptLines, TranscriptLineDTO.class);
    }

    private void updateTranscriptItem(List<TranscriptItemDTO> transcriptItemDTOs, TranscriptLine transcriptLine) {
        int index = 0;
        for (TranscriptItem item : transcriptLine.getTranscriptItems()) {
            item.setPoint(transcriptItemDTOs.get(index).getPoint());
            item.setLastUpdatedDate(DateUtils.now());
            index++;
        }
        transcriptItemRepository.saveAll(transcriptLine.getTranscriptItems());
    }

    private List<TranscriptLineDTO> handleOutputTranscriptLine(List<TranscriptLineDTO> transcriptLineDTOS) {
        for (TranscriptLineDTO transcriptLineDTO : transcriptLineDTOS) {
            transcriptLineDTO.setStudyClass(null);
            for (TranscriptItemDTO item : transcriptLineDTO.getTranscriptItems()) {
                if (Objects.isNull(item.getSubjectPoint())) {
                    continue;
                }
                item.getSubjectPoint().setSubject(null);
            }
        }
        return transcriptLineDTOS;
    }

    private boolean checkSubjectPoint(List<Integer> transcriptLineIds, int pointId) {
        List<TranscriptItem> transcriptItems = transcriptItemRepository.getTranscriptItemsByPointId(transcriptLineIds, pointId);
        if (transcriptItems.size() == transcriptLineIds.size()) {
            return false;
        }
        return true;
    }

    private int statisticFailFinalPoint(List<Integer> transcriptLineIds) {
        List<TranscriptItem> transcriptItems = transcriptItemRepository.getTranscriptItemsByPointFinal(transcriptLineIds, 6);
        int countStudentFail = 0;
        if (this.checkSubjectPoint(transcriptLineIds, 6)) {
            for (TranscriptItem item : transcriptItems) {
                if (item.getPoint().compareTo(0F) == 0 && item.getPoint().compareTo(0F) == -1) {
                    countStudentFail++;
                }
            }
        }
        return countStudentFail;
    }

    private int statisticStudentNonEligible(List<Integer> transcriptLineIds, List<TranscriptLineDTO> transcriptLineDTOs) {
        int countStudentFail = 0;
        for (TranscriptLineDTO lineDTO : transcriptLineDTOs) {
            for (TranscriptItemDTO itemDTO : lineDTO.getTranscriptItems()) {
                if (Objects.isNull(itemDTO.getSubjectPoint())) {
                    continue;
                }
                if (checkSubjectPoint(transcriptLineIds, itemDTO.getSubjectPoint().getPoint().getId()) &&
                        itemDTO.getPoint().compareTo(0F) != 1) {
                    countStudentFail++;
                    break;
                }
            }
        }
        return countStudentFail;
    }

    private void sortTranscriptItems(List<TranscriptLineDTO> transcriptLineDTOs) {
        for (TranscriptLineDTO lineDTO : transcriptLineDTOs) {
            Collections.sort(lineDTO.getTranscriptItems(), (o1, o2) ->
                    o1.getSubjectPoint().getPoint().getId().compareTo(
                    o2.getSubjectPoint().getPoint().getId()));
        }
    }

    private void handlePointFinal(List<TranscriptLineDTO> transcriptLineDTOs, List<SubjectPointDTO> subjectPointDTOs) {
        for (TranscriptLineDTO lineDTO : transcriptLineDTOs) {
            lineDTO.getTranscriptItems().add(this.calPointFinal(lineDTO, subjectPointDTOs));
        }
    }

    private TranscriptItemDTO calPointFinal(TranscriptLineDTO transcriptLine, List<SubjectPointDTO> subjectPoints) {
        Float pointFinal = 0F;
        for (SubjectPointDTO subjectPoint : subjectPoints) {
            TranscriptItem transcriptItem = transcriptItemRepository.getTranscriptItemByTranscriptLine_IdAndSubjectPoint_Id(
                    transcriptLine.getId(), subjectPoint.getId());
            if (Objects.nonNull(transcriptItem.getPoint())) {
                pointFinal += transcriptItem.getPoint() * (subjectPoint.getFactor() / 100F);
            }
        }
        return new TranscriptItemDTO(pointFinal, POINT_FINAL_DESCRIPTION);
    }

    private List<Integer> getTranLineIdPass(List<TranscriptLineDTO> transcriptLineDTOs) {
        List<Integer> transcriptLineIds = new ArrayList<>();
        for (TranscriptLineDTO lineDTO : transcriptLineDTOs) {
            TranscriptItemDTO itemDTO = lineDTO.getTranscriptItems().get(lineDTO.getTranscriptItems().size() - 1);
            if (itemDTO.getPoint().compareTo(4F) == 0 || itemDTO.getPoint().compareTo(4F) == 1) {
                transcriptLineIds.add(lineDTO.getId());
            }
        }
        return transcriptLineIds;
    }

    public RequestDTO sendRequest(RequestDTO requestDTO){
        Date dateReq = DateUtils.addMinutes(Date.from(DateUtils.now()), 3);
        Request request = new Request(requestDTO, dateReq);
        return mappingHelper.map(requestRepository.save(request), RequestDTO.class);
    }

    public BaseResponse approveRequest(String usernameApprove, int id, int status){
        Request request = requestRepository.getById(id);

        request.setUsernameApprove(usernameApprove);
        request.setStatus(status);
        request.setLastUpdatedDate(DateUtils.now());
        requestRepository.save(request);

        return new BaseResponse();
    }

    /**
     * #TODO thay message tra ra
     * @param id
     * @return
     */
    public BaseResponse checkRequest(int id){
        Request request = requestRepository.getById(id);
        Date dateInput = Date.from(DateUtils.now());
        if (request.getExpireDate().compareTo(dateInput) == -1){
            throw new BadRequestAlertException();
        }
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(mappingHelper.map(request, RequestDTO.class));

        return baseResponse;

    }


    public BaseResponse getRequests(){
        Date dateInput = Date.from(DateUtils.now());
        List<Request> requests = requestRepository.getRequests(dateInput);
        return new BaseResponse(200, mappingHelper.mapList(requests, RequestDTO.class));
    }

}
