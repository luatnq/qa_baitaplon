package com.example.qa.service.impl;


import com.example.qa.data.dto.request.LoginReqDTO;
import com.example.qa.data.entity.Staff;
import com.example.qa.data.entity.Teacher;
import com.example.qa.data.entity.User;
import com.example.qa.dto.UserDTO;
import com.example.qa.exception.ResourceNotFoundException;
import com.example.qa.exception.UnauthorizedException;
import com.example.qa.repository.StaffRepository;
import com.example.qa.repository.TeacherRepository;
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
    private final TeacherRepository teacherRepository;
    private final StaffRepository staffRepository;

    private final String roleTeacher = "ROLE_TEACHER";
    private final String roleStaff = "ROLE_STAFF";

    public UserDTO login(LoginReqDTO loginReqDTO) {
        User user = userRepository.findByUsername2(loginReqDTO.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (Objects.nonNull(user) && passwordEncoder.matches(loginReqDTO.getPassword(), user.getPassword())) {
//            String role = checkRole(user);
            if (user.getRole().equals(roleTeacher)) {
                Teacher teacher = teacherRepository.getById(user.getId());
                return new UserDTO(user.getFullName(), teacher.getMajor(), roleTeacher, user.getUsername());
            } else {
                Staff staff = staffRepository.getById(user.getId());
                return new UserDTO(user.getFullName(), staff.getPosition(), roleStaff, user.getUsername());
            }
        }
        throw new UnauthorizedException();
    }

}
