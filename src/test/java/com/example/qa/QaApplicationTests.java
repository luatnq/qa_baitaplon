package com.example.qa;

import com.example.qa.dto.TranscriptOverview;
import com.example.qa.service.TranscriptLineService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;

import static org.junit.Assert.assertEquals;

@SpringBootTest
//@RunWith(SpringRunner.class)
class QaApplicationTests {

    @Autowired
    private TranscriptLineService transcriptLineService;



    @Test
    void contextLoads() {
    }

    @Test
//    @ElementCollection(fetch = FetchType.LAZY)
//    @Test(expected = org.hibernate.LazyInitializationException.class)
    public void statisticStudentFailTest1(){
        final int studyClassId = 1;
        final int subjectId = 1;
        TranscriptOverview transcriptOverview = transcriptLineService.getTranscript(studyClassId, subjectId);

        assertEquals(transcriptOverview.getStatisticStudentFail(), 1);
    }
//    public void testAdd() {
//        String str = "Junit is working fine";
//        assertEquals("Junit is working fine",str);
//    }

}
