package com.example.task.mapper;


import com.example.task.entity.Category;
import com.example.task.entity.Product;
import com.example.task.enums.ProductStatus;
import com.example.task.model.CategoryModel;
import com.example.task.model.ProductModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import org.mapstruct.Mapper;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface ProductMapper extends GenericMapper<Product, ProductModel> {


}
