package com.example.task.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private String keyword = "";

    private int page = 1;

    private int size = 50;

    private String status="";

    private String role="";
}
