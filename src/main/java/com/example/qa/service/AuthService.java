package com.example.qa.service;

import com.example.qa.data.dto.request.LoginReqDTO;

public interface AuthService {
    boolean login(LoginReqDTO loginReqDTO);
}
