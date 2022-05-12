package com.example.qa.controller;


import com.example.qa.data.dto.request.LoginReqDTO;
import com.example.qa.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDTO loginReq) {

        return new ResponseEntity<>(authService.login(loginReq), HttpStatus.OK);
    }

    @PostMapping("/user-info")
    public ResponseEntity<?> getUserInfo(@RequestBody LoginReqDTO loginReq) {

        return new ResponseEntity<>(authService.getUserInfo(loginReq), HttpStatus.OK);
    }
}
