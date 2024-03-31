package com.example.task.service;


import com.example.task.entity.Category;
import com.example.task.entity.Product;
import com.example.task.enums.CategoryStatus;
import com.example.task.enums.ProductStatus;
import com.example.task.exception.CustomException;
import com.example.task.exception.GeneralApiException;
import com.example.task.mapper.ProductMapper;
import com.example.task.model.ProductResponseDto;
import com.example.task.model.GenericResponse;
import com.example.task.model.ProductDto;
import com.example.task.model.ProductModel;
import com.example.task.model.pagination.CustomPage;
import com.example.task.projection.ProductProjection;
import com.example.task.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public CustomPage<ProductProjection> getAllProduct(ProductDto productDto) {

        Pageable pageable = PageRequest.of(productDto.getPage() - 1, productDto.getSize());


        Page<ProductProjection> productList =
                productRepository.getAllProduct(productDto.getKeyword(),
                        pageable);
        return CustomPage.genericPaginationResponse(productList);


    }

    @Cacheable(value = "products", key = "#id")
    public ProductResponseDto  getProduct(Long id) {
        ProductProjection product = productRepository.getProduct(id);

        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .status(product.getStatus())
                .categoryName(product.getCategoryName())
                .expireDate(product.getExpireDate())
                .quantity(product.getQuantity())
                .build();

    }


    public GenericResponse deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new GeneralApiException("PRODUCT_NOT_FOUND"));
        product.setStatus(ProductStatus.DELETED);
        productRepository.save(product);
        return GenericResponse.successMsg();
    }

    public GenericResponse addProduct(ProductModel product) {
        Product newProduct = productMapper.toEntity(product);
        Product savedProduct = productRepository.save(newProduct);
        return GenericResponse.successMsg(savedProduct);
    }




    @Caching(put = @CachePut(value = "products", key = "#id"),
            evict = @CacheEvict(value = "products", key = "'list'"))
    public GenericResponse editProduct(ProductModel updatedProduct, Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new CustomException("PRODUCT NOT FOUND"));
        productMapper.updateEntityFromModel(product, updatedProduct);
        productRepository.save(product);
        return GenericResponse.successMsg();
    }
}
