package com.example.task.controller;


import com.example.task.model.GenericResponse;
import com.example.task.model.ProductDto;
import com.example.task.model.ProductModel;
import com.example.task.model.ProductResponseDto;
import com.example.task.model.pagination.CustomPage;
import com.example.task.projection.ProductProjection;
import com.example.task.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.task.common.utils.Constants.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public GenericResponse getProducts(ProductDto productDto) {
        CustomPage<ProductProjection> productList = productService.getAllProduct(productDto);
        return GenericResponse.successMsg(productList);
    }

    @GetMapping("/{id}")
    public GenericResponse getProduct(@PathVariable Long id) {
        ProductResponseDto product = productService.getProduct(id);
        return GenericResponse.successMsg(product);
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    @PostMapping()
    @Operation(requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(examples = @ExampleObject(value = "{\n" +
                    "  \"name\": \"Product\",\n" +
                    "  \"status\": \"ACTIVE\",\n" +
                    "  \"quantity\": 10,\n" +
                    "  \"expireDate\": \"2024-12-31\",\n" +
                    "  \"category\": {\n" +
                    "    \"id\": 1,\n" +
                    "    \"name\": \"Category\",\n" +
                    "    \"status\": \"ACTIVE\"\n" +
                    "  }\n" +
                    "}"))))
    public GenericResponse addProduct(@RequestBody ProductModel product) {
        return productService.addProduct(product);

    }

    @PutMapping("/{id}")
    @Operation(requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(examples = @ExampleObject(value = "{\n" +
                    "  \"name\": \"Product\",\n" +
                    "  \"status\": \"ACTIVE\",\n" +
                    "  \"quantity\": 10,\n" +
                    "  \"expireDate\": \"2024-12-31\",\n" +
                    "  \"category\": {\n" +
                    "    \"id\": 1,\n" +
                    "    \"name\": \"Category\",\n" +
                    "    \"status\": \"ACTIVE\"\n" +
                    "  }\n" + "}"))))
    public GenericResponse editProduct(@RequestBody ProductModel product, @PathVariable Long id) {
        return productService.editProduct(product, id);
    }


}
