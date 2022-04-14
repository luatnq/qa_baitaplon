package com.example.qa.service.impl;


import com.example.qa.data.dto.request.LoginReqDTO;
import com.example.qa.data.entity.User;
import com.example.qa.repository.UserRepository;
import com.example.qa.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public boolean login(LoginReqDTO loginReqDTO) {
        User user = userRepository.findByUsername(loginReqDTO.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (Objects.nonNull(user) && passwordEncoder.matches(loginReqDTO.getPassword(), user.getPassword())) {
            return true;
        }
        return false;
    }
}
