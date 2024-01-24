
package com.example.login.controller;
import com.example.login.dto.LoginDto;
import com.example.login.dto.PasswordDto;
import com.example.login.dto.UserDto;
import com.example.login.service.impl.ServiceTesteImpl;
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
        } catch (ServiceException e) {
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
        try {
            if (loginDto.getEmail() == null || loginDto.getPassword() == null) {
                return ResponseEntity.badRequest().body(new LoginResponse("Todos os campos devem ser preenchidos.", false));
            }
            LoginResponse loginResponse = teste.loginUser(loginDto);
            return ResponseEntity.ok(loginResponse);
        } catch (ServiceException e) {
            return ResponseEntity.status(500).body(new LoginResponse("Erro durante o cadastro: " + e.getMessage(), false));
        }
    }
    @PutMapping(path = "/changepass")
    public ResponseEntity<?> updateUserPassword(@RequestBody PasswordDto passDto) {
        try {
            if (passDto.getEmail() == null || passDto.getOldPass() == null || passDto.getNewPass() == null) {
                return ResponseEntity.badRequest().body(new LoginResponse("Os campos não podem ser nulos", false));
            }
            LoginResponse loginResponse = teste.updatePassword(passDto);
            return ResponseEntity.ok(loginResponse);
        } catch (ServiceException e) {
            return ResponseEntity.status(500).body(new LoginResponse("Erro durante a atualização da senha: " + e.getMessage(), false));
        }
    }

    @GetMapping(path = "/listUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> listUsers() {
        List<UserDto> listUsers = teste.listUsers();
        if (listUsers == null) {
            return ResponseEntity.status(500).body("Erro ao buscar usuários.");
        } else if (listUsers.isEmpty()) {
            return ResponseEntity.status(404).body("Nenhum usuário encontrado.");
        } else {
            return ResponseEntity.ok(listUsers);
        }
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LoginResponse> deleteUser(@PathVariable int userId) {
        try {
            LoginResponse response = teste.deleteUser(userId);
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.status(500).body(new LoginResponse("Erro durante o cadastro: " + e.getMessage(), false));
        }
    }
}




