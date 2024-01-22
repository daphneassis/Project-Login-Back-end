package com.example.login.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

//@Configuration
//@EnableWebSecurity
public class Teste1  {
//    public class Teste1 extends WebSecurityConfigurerAdapter {


        //comentado o código abaixo, referência circular, junto com Teste2 tb não funciona
//
//    //    private final PasswordEncoder passwordEncoder;
////
////    @Autowired
////    public SecurityConfig(PasswordEncoder passwordEncoder) {
////        this.passwordEncoder = passwordEncoder;
////    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors()
//                .and()
//                .httpBasic()
//                .and()
//                .authorizeHttpRequests()
////                .antMatchers(HttpMethod.GET, "/parking-spot/**").permitAll()
////                .antMatchers(HttpMethod.POST, "/parking-spot").hasRole("USER")
////                .antMatchers(HttpMethod.DELETE, "/parking-spot/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable();
//    }
//
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
//        configuration.setExposedHeaders(Arrays.asList("Authorization"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}