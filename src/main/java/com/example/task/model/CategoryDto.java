package com.example.task.model;


import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDto implements Serializable {

    private Long id;

    private String name;

    private String status;

    private Long count;
}
