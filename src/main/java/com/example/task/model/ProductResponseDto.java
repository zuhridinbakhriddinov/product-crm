package com.example.task.model;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponseDto implements Serializable {


    private Long id;
    private String name;
    private Integer quantity;
    private String categoryName;
    private String status;
    private LocalDate expireDate;


}
