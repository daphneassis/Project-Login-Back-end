package com.example.login.service;

import com.example.login.dto.LoginDto;
import com.example.login.dto.UserDto;
import response.LoginResponse;

import java.util.List;


public interface UserService {

    LoginResponse addUser(UserDto userDto);

    LoginResponse loginUser(LoginDto loginDto);

    List<UserDto> getAllUsers();

    void deleteUser(String userId);
}
