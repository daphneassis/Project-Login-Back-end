
package com.example.login.controller;

import com.example.login.dto.LoginDto;
import com.example.login.dto.UserDto;
import com.example.login.service.impl.ServiceTesteImpl;
import org.apache.coyote.Response;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import response.LoginResponse;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final ServiceTesteImpl teste;

    public UserController(ServiceTesteImpl teste) {
        this.teste = teste;
    }


    @PostMapping(path = "/save")
    public ResponseEntity<LoginResponse> saveUser(@RequestBody UserDto userDto) {
        try {
            ResponseEntity<LoginResponse> body = validationUserDto(userDto);
            if (body != null) return body;
            LoginResponse response = teste.addUser(userDto);
            return ResponseEntity.ok(response);
        } catch(ServiceException e) {
        return ResponseEntity.status(500).body(new LoginResponse("Erro durante o cadastro: " + e.getMessage(), false));
    }

}

    private ResponseEntity<LoginResponse> validationUserDto(UserDto userDto) {
        if (userDto.getUsername() == null || userDto.getEmail() == null || userDto.getPassword() == null || userDto.getCpf() == null || userDto.getRole() == null) {
            return ResponseEntity.badRequest().body(new LoginResponse("Todos os campos devem ser preenchidos.", false));
        }
        if (!userDto.getRole().equalsIgnoreCase("ADMIN") && !userDto.getRole().equalsIgnoreCase("USER")) {
            return ResponseEntity.badRequest().body(new LoginResponse("A permissão só pode ser ADMIN ou USER.", false));
        }
        return null;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        LoginResponse loginResponse = teste.loginUser(loginDto);
        return ResponseEntity.ok(loginResponse);
    }
    @GetMapping(path = "/listar")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UserDto>> listUsers() {
        List<UserDto> users = teste.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/deletar/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        teste.deleteUser(userId);
        return ResponseEntity.ok("Usuário excluído com sucesso!");
    }

//    @Autowired
//    private UserService userService;
//
//    @PostMapping(path = "/save")
//    public String saveUser(@RequestBody UserDto userDto) {
//        String id = userService.addUser(userDto);
//        return id;
//    }
//    @PostMapping(path = "/login")
//    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
//        LoginResponse loginResponse = userService.loginUser(loginDto);
//        return ResponseEntity.ok(loginResponse);
//    }
//    @GetMapping(path = "/listar")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<List<UserDto>> listUsers() {
//        List<UserDto> users = userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }
//
//    @DeleteMapping("/deletar/{userId}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
//        userService.deleteUser(userId);
//        return ResponseEntity.ok("Usuário excluído com sucesso!");
//    }

}

