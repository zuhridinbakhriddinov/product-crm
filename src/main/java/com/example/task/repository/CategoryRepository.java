package com.example.task.repository;

import com.example.task.entity.Category;
import com.example.task.projection.CategoryProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends BaseRepository<Category> {

    @Query(nativeQuery = true, value = "select c.id as id, c.name as name, c.status as status, count(p) as count \n" +
            "from categories c\n" +
            "       left  join products p on c.id = p.category_id\n" +
            "where c.id = :id\n" +
            "group by c.id")
    CategoryProjection getCategory(@Param("id") Long id);
}
