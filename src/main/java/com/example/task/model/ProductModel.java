package com.example.task.model;

import com.example.task.entity.Category;
import com.example.task.enums.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductModel {

    private String name;
    private ProductStatus status;
    private Integer quantity;
    private LocalDate expireDate;
    private CategoryModel category;
}
