package com.example.qa;

import com.example.qa.data.dto.request.LoginReqDTO;
import com.example.qa.dto.BaseResponse;
import com.example.qa.dto.RequestDTO;
import com.example.qa.dto.TranscriptOverview;
import com.example.qa.dto.UserDTO;
import com.example.qa.exception.ResourceNotFoundException;
import com.example.qa.exception.UnauthorizedException;
import com.example.qa.service.AuthService;
import com.example.qa.service.TranscriptLineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class QaApplicationTests {

    @Autowired
    private TranscriptLineService transcriptLineService;

    @Autowired
    private AuthService authService;

    @Test
    /**
     * password incorrect
     */
    public void loginTest1() {
        final LoginReqDTO loginReq = new LoginReqDTO("luatnq", "1234");
        try {
            UserDTO userDTO = authService.login(loginReq);
        } catch (UnauthorizedException e) {
            assertEquals(401, e.getQAError().getErrorCode());
        }

    }

    @Test
    /**
     * username not exist
     */
    public void loginTest2() {
        final LoginReqDTO loginReq = new LoginReqDTO("luatnq1", "123");
        try {
            UserDTO userDTO = authService.login(loginReq);
        } catch (ResourceNotFoundException e) {
            assertEquals(404, e.getQAError().getErrorCode());
        }

    }

    @Test
    /**
    * login success
    */
    public void loginTest3() {
        final LoginReqDTO loginReq = new LoginReqDTO("luatnq", "123");
        UserDTO userDTO = authService.login(loginReq);
        assertEquals(userDTO.getUsername(), loginReq.getUsername());
    }

    @Test
    /**
     * send request
     */
    public void sendRequestTest1(){
        RequestDTO reqDTO = new RequestDTO("luatnq", 0, 1, 1);
        RequestDTO requestRes = transcriptLineService.sendRequest(reqDTO);

        assertEquals(reqDTO.getUsernameReq(), requestRes.getUsernameReq());
        assertEquals(reqDTO.getStatus(), requestRes.getStatus());
        assertEquals("Đảm bảo chất lượng phần mềm", requestRes.getSubjectName());
        assertEquals("Nhóm 2", requestRes.getClassName());
        transcriptLineService.deleteById(requestRes.getId());
    }

    @Test
    /**
     * approve request: accept request by staff
     */
    public void approveRequestTest1(){
        RequestDTO reqDTO = new RequestDTO("luatnq", 0, 1, 1);
        RequestDTO requestRes = transcriptLineService.sendRequest(reqDTO);
        BaseResponse baseResponse = transcriptLineService.approveRequest("staff", requestRes.getId(), 1);

        RequestDTO reqFinal = (RequestDTO) baseResponse.getData();
        assertEquals(1, reqFinal.getStatus());
        assertEquals("staff", reqFinal.getUsernameApprove());
        transcriptLineService.deleteById(requestRes.getId());
    }

    @Test
    /**
     * approve request: reject request by staff
     */
    public void approveRequestTest2(){
        RequestDTO reqDTO = new RequestDTO("luatnq", 0, 1, 1);
        RequestDTO requestRes = transcriptLineService.sendRequest(reqDTO);
        BaseResponse baseResponse = transcriptLineService.approveRequest("staff", requestRes.getId(), 2);

        RequestDTO reqFinal = (RequestDTO) baseResponse.getData();
        assertEquals(2, reqFinal.getStatus());
        assertEquals("staff", reqFinal.getUsernameApprove());
        transcriptLineService.deleteById(requestRes.getId());
    }
}
