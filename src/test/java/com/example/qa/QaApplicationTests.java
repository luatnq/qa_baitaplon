package com.example.qa;

import com.example.qa.service.TranscriptLineService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
//@RunWith(SpringRunner.class)
class QaApplicationTests {

    @Autowired
    private TranscriptLineService transcriptLineService;

    @Test
    void contextLoads() {
    }

//    public void statisStudentFailTest1
//    public void testAdd() {
//        String str = "Junit is working fine";
//        assertEquals("Junit is working fine",str);
//    }

}
