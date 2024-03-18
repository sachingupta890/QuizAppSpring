package com.Quiz.quizapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    static PasswordEncoder passwordEncoder()
    {

        return new BCryptPasswordEncoder();

    }

}
