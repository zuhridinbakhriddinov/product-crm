package com.example.task.entity;


import com.example.task.enums.CategoryStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "categories")
public class Category extends BaseEntity{
    private String name;
    @Enumerated(value = EnumType.STRING)
    private CategoryStatus status;
}
