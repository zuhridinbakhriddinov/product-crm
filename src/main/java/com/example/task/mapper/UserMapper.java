package com.example.task.mapper;


import com.example.task.entity.Product;
import com.example.task.entity.User;
import com.example.task.model.ProductModel;
import com.example.task.model.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserModel> {


}
