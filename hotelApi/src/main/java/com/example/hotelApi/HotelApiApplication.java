package com.example.hotelApi;

import com.example.hotelApi.dto.SignUpDTO;
import com.example.hotelApi.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HotelApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApiApplication.class, args);
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Bean
	CommandLineRunner run(AuthService accountService){
		return args -> {
			SignUpDTO signUpDTO = new SignUpDTO();
			signUpDTO.setPassword("1234");
			signUpDTO.setNickname("test");
			signUpDTO.setEmail("syseoz@naver.com");

			accountService.signUp(signUpDTO);
		};
	}

}
