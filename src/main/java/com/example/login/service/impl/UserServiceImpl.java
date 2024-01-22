package com.example.login.service.impl;

import com.example.login.dto.LoginDto;
import com.example.login.dto.UserDto;
import com.example.login.entity.UserEntity;
import com.example.login.enums.Role;
import com.example.login.repository.UserRepository;
import com.example.login.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import response.LoginResponse;

public class UserServiceImpl{

}
//@Service
//@Transactional
//public class UserServiceImpl implements UserService, UserDetailsService {
//
//    private  UserRepository userRepository;
////    private final PasswordEncoder passwordEncoder;
//
//  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
////        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public String addUser(UserDto userDto) {
//        UserEntity user = new UserEntity();
//       user.setUsername(userDto.getUsername());
//       user.setEmail(userDto.getEmail());
//       user.setPassword(userDto.getPassword());
////       user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//       user.setCpf(userDto.getCpf());
//       RolesEntity rolesEntity = getRolesEntity(userDto);
//       user.setRole(rolesEntity);
//       userRepository.save(user);
//       userRepository.save(user);
//       return user.getUsername();
//    }
//
//    private RolesEntity getRolesEntity(UserDto userDto) {
//        String roleString = userDto.getRole().toUpperCase();
//        Role role = Role.valueOf(roleString);
//        RolesEntity rolesEntity = new RolesEntity();
//        rolesEntity.setRoleName(role);
//        return rolesEntity;
//    }
//
//
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
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity user = userRepository.findByUsername(username)
//                .orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado com esse nome" + username));
//        return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
//    }
//}