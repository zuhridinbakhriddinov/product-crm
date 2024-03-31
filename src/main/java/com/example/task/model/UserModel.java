package com.example.task.model;


import com.example.task.enums.UserRole;
import com.example.task.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModel {

    private String name;
    private UserStatus userStatus;
    private UserRole role;
    private String login;
    private String password;
}
