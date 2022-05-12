package com.example.qa;

import com.example.qa.data.dto.request.LoginReqDTO;
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
//@RunWith(SpringRunner.class)
class QaApplicationTests {

    @Autowired
    private TranscriptLineService transcriptLineService;

    @Autowired
    private AuthService authService;


    @Test
    void contextLoads() {
    }

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

//    @Test
//    @ElementCollection(fetch = FetchType.LAZY)
    @Test
    public void statisticStudentFailTest1(){
        final int studyClassId = 1;
        final int subjectId = 1;
        TranscriptOverview transcriptOverview = transcriptLineService.getTranscript(studyClassId, subjectId);

        assertEquals(1, transcriptOverview.getStatisticStudentFail());
    }
//    public void testAdd() {
//        String str = "Junit is working fine";
//        assertEquals("Junit is working fine",str);
//    }


}
