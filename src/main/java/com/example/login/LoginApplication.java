package com.example.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication()
//exclude = {SecurityAutoConfiguration.class }
@RestController
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("senha123"));
		System.out.println(new BCryptPasswordEncoder().encode("senha456"));
		System.out.println(new BCryptPasswordEncoder().encode("senha789"));
		System.out.println(new BCryptPasswordEncoder().encode("senha10"));

	}


//	@RequestMapping("/")
//	public String index(){
//		return "Olá mundo";
//	}

}
