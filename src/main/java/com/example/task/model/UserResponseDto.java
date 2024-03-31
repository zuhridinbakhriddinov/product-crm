package com.example.task.model;


import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDto implements Serializable {

    private Long id;

    private String name;

    private String role;

    private String status;

    private String login;


}

