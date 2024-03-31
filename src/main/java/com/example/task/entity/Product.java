package com.example.task.entity;

import com.example.task.enums.ProductStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseEntity{

    private String name;
    @Enumerated(value = EnumType.STRING)
    private ProductStatus status;
    private Integer quantity;
    private LocalDate expireDate;

    @ManyToOne
    private Category category;
}
