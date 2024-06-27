package com.in28minutes.springboot.myfirstwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		UserDetails userDetails1 = createNewUser("SpringBootJSP", "ILoveSpring");
		UserDetails userDetails2 = createNewUser("EH13", "backend");

		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public UserDetails createNewUser(String username, String password) {
		return User.builder()
				.passwordEncoder(passwordEncoder()::encode)
				.username(username)
				.password(password)
				.roles("USER", "ADMIN")
				.build();
	}
}
