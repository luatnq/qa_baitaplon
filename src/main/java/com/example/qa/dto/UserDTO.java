package com.example.qa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String fullName;

    private String major;

    private String position;

    private String role;

    public UserDTO(String fullName, String major, String role){
        this.fullName = fullName;
        if ("ROLE_TEACHER".equals(role)){
            this.major = major;
        }else {
            this.position = major;
        }
        this.role = role;
    }
//
//    public UserDTO(String fullName, String position, String role){
//        this.fullName = fullName;
//        this.position = position;
//        this.role = role;
//    }


}
