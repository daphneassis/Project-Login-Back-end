package com.example.login.service.impl;
import com.example.login.dto.LoginDto;
import com.example.login.dto.UserDto;
import com.example.login.entity.UserEntity;
import com.example.login.enums.Role;
import com.example.login.repository.UserRepository;
import com.example.login.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import response.LoginResponse;

import java.util.List;

@Service
@Transactional
public class ServiceTesteImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ServiceTesteImpl() {
    }

//    @Override
//    public String addUser(UserDto userDto) {
//        UserEntity user = new UserEntity();
//       user.setUsername(userDto.getUsername());
//       user.setEmail(userDto.getEmail());
////    user.setPassword(userDto.getPassword());
//      user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//       user.setCpf(userDto.getCpf());
//        String roleString = userDto.getRole().toUpperCase();
//        Role role = Role.valueOf(roleString);
//       user.setRole(role);
//       userRepository.save(user);
//       return user.getUsername();
//    }

    @Override
    public LoginResponse addUser(UserDto userDto) {
        try {
            UserEntity user = new UserEntity();
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
//    user.setPassword(userDto.getPassword());
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

    //    @Override
//    public LoginResponse loginUser(LoginDto loginDto) {
//        UserEntity user1 = userRepository.findByEmail(loginDto.getEmail());
//        if (user1 != null) {
//            String password = loginDto.getPassword();
//            String encodedPassword = user1.getPassword();
//            Boolean isPasswordCorrect = password.equals(encodedPassword);
////            Boolean isPasswordCorrect = passwordEncoder.matches(password, encodedPassword);
//            if (isPasswordCorrect) {
//                    return new LoginResponse("Sucesso no Login", true);
//                } else {
//                return new LoginResponse("Senha incorreta", false);
//            }
//        }
//        else{
//                return new LoginResponse("Email não encontrado", false);
//            }
//        }

    //fazer uma validação do enum aqui    return new LoginResponse("Senha incorreta", false);

    @Override
    public LoginResponse loginUser(LoginDto loginDto) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }
}
