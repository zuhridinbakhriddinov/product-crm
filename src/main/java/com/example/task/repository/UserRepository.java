package com.example.task.repository;

import com.example.task.entity.User;
import com.example.task.enums.UserStatus;
import com.example.task.projection.ProductProjection;
import com.example.task.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {


    Optional<User> findByLoginAndUserStatus(String username, UserStatus status);


    @Query(nativeQuery = true, value = """
            select u.id as id, u.name as name, u.role as role, u.user_status as status, u.login as login
            from users u
            where (concat('', u.id) = :keyword
                or lower(u.login) like lower(concat('%', :keyword, '%'))
                or lower(u.name) like lower(concat('%', :keyword, '%')))
              and (:role = ''  or :role = u.role)
              and (:status = ''or :status = u.user_status)
            """)
    Page<UserProjection> getAllUser(@Param("keyword") String keyword, @Param("role") String role,
                                    @Param("status") String status, Pageable pageable);


    @Query(nativeQuery = true, value = """
            select u.id as id, u.name as name, u.role as role, u.user_status as status, u.login as login
            from users u
            where u.id= :id
                        """)
    UserProjection getUser(@Param("id") Long id);


    boolean existsByLogin(String login);
}
