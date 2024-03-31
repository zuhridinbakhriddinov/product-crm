package com.example.task.controller;


import com.example.task.model.DeleteCategoryModel;
import com.example.task.model.CategoryRequestModel;
import com.example.task.model.GenericResponse;
import com.example.task.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.task.common.utils.Constants.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping()
    public GenericResponse getCategoryList() {
        return categoryService.getAllCategory();
    }

    @PostMapping()
    public GenericResponse addCategory(CategoryRequestModel category) {
        return categoryService.addCategory(category);
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/{id}")
    public GenericResponse getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }


}
