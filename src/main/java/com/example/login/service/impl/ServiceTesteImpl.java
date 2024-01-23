package com.example.login.service.impl;
import com.example.login.dto.LoginDto;
import com.example.login.dto.UserDto;
import com.example.login.entity.UserEntity;
import com.example.login.enums.Role;
import com.example.login.repository.UserRepository;
import com.example.login.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import response.LoginResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceTesteImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ServiceTesteImpl() {
    }

    @Override
    public LoginResponse addUser(UserDto userDto) {
        try {
            UserEntity user = new UserEntity();
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setCpf(userDto.getCpf());
            String roleString = userDto.getRole().toUpperCase();
            Role role = Role.valueOf(roleString);
            user.setRole(role);
            userRepository.save(user);
            return new LoginResponse("Cadastro realizado com sucesso!", true);
        } catch (Exception e) {
            throw new ServiceException("Erro durante o cadastro", e);
        }
    }

    @Override
    public LoginResponse loginUser(LoginDto loginDto) {
        UserEntity user = userRepository.findByEmail(loginDto.getEmail());
        if (user != null) {
            String password = loginDto.getPassword();
            String encodedPassword = user.getPassword();
            Boolean isPasswordCorrect = passwordEncoder.matches(password, encodedPassword);
            if (isPasswordCorrect) {
                return new LoginResponse("Sucesso no Login", true);
            } else {
                return new LoginResponse("Senha incorreta", false);
            }
        } else {
            return new LoginResponse("Email não encontrado", false);
        }
    }

    public boolean verifyAccess(LoginDto loginDto) {
        UserEntity user = userRepository.findByEmail(loginDto.getEmail());
        if (user.getRole().toString() == "ADMIN") {
            return true;
        } else {
            return false;
        }
    }

    public UserDto populateDto(UserEntity userEntity) {
        UserDto dto = new UserDto();
        dto.setUsername(userEntity.getUsername());
        dto.setEmail(userEntity.getEmail());
        dto.setCpf(userEntity.getCpf());
        dto.setPassword(userEntity.getPassword());
        dto.setRole(userEntity.getRole().toString());
        dto.setUserId(userEntity.getUserId());
        return dto;
    }

    public List<UserDto> findUsersByRole(LoginDto loginDto) {
        if (verifyAccess(loginDto)) {
            List<UserDto> listaDto = new ArrayList<>();
            List<UserEntity> listaAll = userRepository.findAll();
            for (UserEntity userEntity : listaAll) {
                if (userEntity.getRole().toString().equals("USER")) {
                    UserDto dto = populateDto(userEntity);
                    listaDto.add(dto);
                }
            }
            return listaDto;
        }
        return Collections.emptyList();
    }

    public List<UserDto> listUsers() {
        List<UserDto> listaDto = new ArrayList<>();
        List<UserEntity> listaAll = userRepository.findAll();
        if(listaAll!=null){
        for (UserEntity userEntity : listaAll) {
            if (userEntity.getRole().toString().equals("USER")) {
                UserDto dto = populateDto(userEntity);
                listaDto.add(dto);
            }
        }
        return listaDto;
    }
        return Collections.emptyList();
    }

    @Override
    public LoginResponse deleteUser(int userId) {
//        int userIdInt = Integer.parseInt(userId);
//        UserEntity userDel = userRepository.findByUserId(userId);
        try{
            Optional<UserEntity> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
            UserEntity userDel = userOptional.get();
            userRepository.delete(userDel);
            return new LoginResponse("Sucesso ao deletar o usuário", true);
        } else {
            return new LoginResponse("Usuário não encontrado", false);
        }
    } catch (NumberFormatException ex) {
        return new LoginResponse("Formato inválido para userId", false);
    }
}


    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }




}
