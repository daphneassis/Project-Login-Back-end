package com.example.login.repository;

import com.example.login.dto.LoginDto;
import com.example.login.dto.UserDto;
import com.example.login.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findOneByEmailAndPassword(String email, String password);
    List<UserDto> findByRole(LoginDto loginDto);
    UserEntity findByEmail(String email);
    UserEntity findByUserId(int id);


}
