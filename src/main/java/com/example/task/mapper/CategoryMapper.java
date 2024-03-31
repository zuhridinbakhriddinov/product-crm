package com.example.task.mapper;


import com.example.task.entity.Category;
import com.example.task.model.CategoryModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends GenericMapper<Category, CategoryModel> {
}
