package com.example.qa.service;

import com.example.qa.data.dto.request.LoginReqDTO;
import com.example.qa.dto.UserDTO;

public interface AuthService {
    UserDTO login(LoginReqDTO loginReqDTO);

    UserDTO getUserInfo(LoginReqDTO loginReqDTO);
}
