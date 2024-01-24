package com.example.login.config;

import com.example.login.entity.UserEntity;
import com.example.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    final
    UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado com esse nome" + username));
//        return new User(userEntity.getUsername(), userEntity.getPassword(),Collections.singletonList(new SimpleGrantedAuthority(userEntity.getAuthority())));
        return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.getAuthorities());

    }

    //        return userEntity;
//        Collection<? extends GrantedAuthority> authorities = userEntity.getAuthorities();
//        return new User(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true, authorities);
}
