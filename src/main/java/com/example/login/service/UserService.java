package com.example.login.service;

import com.example.login.dto.LoginDto;
import com.example.login.dto.PasswordDto;
import com.example.login.dto.UserDto;
import com.example.login.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import response.LoginResponse;

import java.util.List;
import java.util.Optional;


public interface UserService {

    LoginResponse addUser(UserDto userDto);

    LoginResponse loginUser(LoginDto loginDto);

    List<UserDto> findUsersByRole(LoginDto loginDto);

    List<UserDto> getAllUsers();

    List<UserDto> listUsers();

    LoginResponse deleteUser(int userId);

    LoginResponse updatePassword(PasswordDto passDto);
}
