package com.example.task.repository;

import com.example.task.entity.Product;
import com.example.task.projection.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends BaseRepository<Product> {


    @Query(nativeQuery = true, value = """
            select p.id as id,
                   p.name as name,
                   p.quantity as quantity,
                   c.name as categoryName,
                   p.status as status,
                   p.expire_date as expireDate
            from products p
                     join categories c on c.id = p.category_id
            where
                concat('',c.id) = :keyword
                           or lower(p.name) like lower(concat('%', :keyword, '%')) or
                            lower(c.name) like lower(concat('%', :keyword, '%'))
            """)
    Page<ProductProjection> getAllProduct(@Param("keyword") String keyword, Pageable pageable);



    @Query(nativeQuery = true, value = """
            select p.id as id,
                   p.name as name,
                   p.quantity as quantity,
                   c.name as categoryName,
                   p.status as status,
                   p.expire_date as expireDate
            from products p
                     join categories c on c.id = p.category_id
            where
                p.id= :id
            """)
    ProductProjection getProduct(@Param("id")Long id);





}
