package com.example.login.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    final
    UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.PATCH, "http://localhost:8080/api/user/listUsers").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/user/save", "/api/user/login").permitAll()
//                .antMatchers(HttpMethod.PUT, "/api/user/changepass").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/user/changepass").permitAll()

//                .antMatchers(HttpMethod.GET, "/api/user/listUsers").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/api/user/delete/**").hasRole("ADMIN")

                .and()
                .csrf().disable()
            ;

    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(passwordEncoder().encode("123"))
//                .roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
