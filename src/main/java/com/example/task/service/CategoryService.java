package com.example.task.service;

import com.example.task.common.utils.Localization;
import com.example.task.entity.Category;
import com.example.task.enums.CategoryStatus;
import com.example.task.exception.GeneralApiException;
import com.example.task.model.CategoryDto;
import com.example.task.model.CategoryRequestModel;
import com.example.task.model.GenericResponse;
import com.example.task.projection.CategoryProjection;
import com.example.task.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public GenericResponse getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        return GenericResponse.successMsg(categoryList);
    }


    public GenericResponse addCategory(CategoryRequestModel category) {
        Category savedCategory = categoryRepository.save(new Category(category.getName(), CategoryStatus.ACTIVE));
        return GenericResponse.successMsg(savedCategory);
    }

    public GenericResponse deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new GeneralApiException("CATEGORY_NOT_FOUND"));
        category.setStatus(CategoryStatus.DELETED);
        categoryRepository.save(category);
        return GenericResponse.successMsg();
    }


    @Cacheable(value = "categories", key = "#id")
    public GenericResponse getCategory(Long id) {

        CategoryProjection category = categoryRepository.getCategory(id);
        CategoryDto newCategory = getCategoryDto(category);
        return GenericResponse.successMsg(newCategory);

    }


    private CategoryDto getCategoryDto(CategoryProjection category) {
        if (category == null) {
            throw new GeneralApiException("CATEGORY_NOT_FOUND");
        }
        return CategoryDto.
                builder()
                .name(category.getName())
                .status(category.getStatus())
                .id(category.getId())
                .count(category.getCount())
                .build();
    }
}
