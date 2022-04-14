package com.example.qa.controller;


import com.example.qa.data.dto.request.LoginReqDTO;
import com.example.qa.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/login")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginReqDTO loginReq){
        if (authService.login(loginReq)){
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.UNAUTHORIZED);
    }

}