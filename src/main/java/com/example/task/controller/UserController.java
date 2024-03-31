package com.example.task.controller;


import com.example.task.model.GenericResponse;
import com.example.task.model.ProductDto;
import com.example.task.model.UserDto;
import com.example.task.model.UserModel;
import com.example.task.model.pagination.CustomPage;
import com.example.task.projection.ProductProjection;
import com.example.task.projection.UserProjection;
import com.example.task.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.example.task.common.utils.Constants.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public GenericResponse getUsers(UserDto userDto) {
        return userService.getAllUsers(userDto);
    }

    @GetMapping("/{id}")
    public GenericResponse getUser(@PathVariable Long id) {

        return userService.getUser(id);
    }

    @PostMapping()
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(examples = @ExampleObject(value = "{\n" +
                    "  \"name\": \"John Doe\",\n" +
                    "  \"userStatus\": \"ACTIVE\",\n" +
                    "  \"role\": \"WORKER\",\n" +
                    "  \"login\": \"johndoe\",\n" +
                    "  \"password\": \"111\"\n" +
                    "}"))))
    public GenericResponse addUser(@RequestBody UserModel userModel) {
        return userService.addUser(userModel);
    }

    @PutMapping("/{id}")
    @Operation(requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(examples = @ExampleObject(value = "{\n" +
                    "  \"name\": \"John Doe\",\n" +
                    "  \"userStatus\": \"ACTIVE\",\n" +
                    "  \"role\": \"WORKER\",\n" +
                    "  \"login\": \"johndoe\",\n" +
                    "  \"password\": \"111\"\n" +
                    "}"))))
    public GenericResponse editUser(@RequestBody UserModel userModel,@PathVariable Long id) {
       return userService.editUser(userModel,id);
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteUser(@PathVariable Long id) {
       return userService.deleteUser(id);
    }


}
