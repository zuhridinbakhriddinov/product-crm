package com.example.task.service;


import com.example.task.common.utils.Localization;
import com.example.task.entity.Product;
import com.example.task.entity.User;
import com.example.task.enums.ProductStatus;
import com.example.task.enums.UserStatus;
import com.example.task.exception.CustomException;
import com.example.task.exception.GeneralApiException;
import com.example.task.mapper.UserMapper;
import com.example.task.model.*;
import com.example.task.model.pagination.CustomPage;
import com.example.task.projection.CategoryProjection;
import com.example.task.projection.ProductProjection;
import com.example.task.projection.UserProjection;
import com.example.task.repository.UserRepository;
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
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public GenericResponse getAllUsers(UserDto userDto) {


        Pageable pageable = PageRequest.of(userDto.getPage() - 1, userDto.getSize());

        Page<UserProjection> userList =
                userRepository.getAllUser(userDto.getKeyword(), userDto.getRole(), userDto.getStatus(), pageable);
        CustomPage<UserProjection> userProjectionCustomPage = CustomPage.genericPaginationResponse(userList);

        return GenericResponse.successMsg(userProjectionCustomPage);
    }

    @Cacheable(value = "users", key = "#id")
    public GenericResponse getUser(Long id) {

        UserProjection user = userRepository.getUser(id);
        UserResponseDto userResponseDto = getUserResponseDto(user);
        return GenericResponse.successMsg(userResponseDto);
    }

    private UserResponseDto getUserResponseDto(UserProjection user) {
        if (user == null) {
            throw new GeneralApiException("USER_NOT_FOUND");
        }
        return UserResponseDto.
                builder()
                .name(user.getName())
                .status(user.getStatus())
                .id(user.getId())
                .role(user.getRole())
                .login(user.getLogin())
                .build();

    }


    public User findByUsername(String username) {
        return userRepository.findByLoginAndUserStatus(username, UserStatus.ACTIVE).orElseThrow(() -> new GeneralApiException("USER_NOT_FOUND"));
    }


    public GenericResponse addUser(UserModel userModel) {
        boolean exists = userRepository.existsByLogin(userModel.getLogin());
        if (exists) {
            throw new GeneralApiException("USER_ALREADY_EXIST");
        }
        User newUser = userMapper.toEntity(userModel);
        User savedUser = userRepository.save(newUser);
        return GenericResponse.successMsg(savedUser);
    }


    @Caching(put = @CachePut(value = "users", key = "#id"),
            evict = @CacheEvict(value = "users", key = "'all'"))
    public GenericResponse editUser( UserModel userModel,Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new GeneralApiException("USER_NOT_FOUND"));
        userMapper.updateEntityFromModel(user, userModel);
        userRepository.save(user);
        return GenericResponse.successMsg();
    }

    public GenericResponse deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new GeneralApiException("USER_NOT_FOUND"));
        user.setUserStatus(UserStatus.DELETED);
        userRepository.save(user);
        return GenericResponse.successMsg();
    }
}
