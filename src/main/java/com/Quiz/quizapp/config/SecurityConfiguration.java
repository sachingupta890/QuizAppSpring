package com.Quiz.quizapp.config;


import com.Quiz.quizapp.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration
{


    @Autowired
    private CustomUserDetailService customUserService;


    @Bean
    static PasswordEncoder passwordEncoder()
    {

        return new BCryptPasswordEncoder();

    }


    @Bean

    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/user/**").permitAll()
                                .requestMatchers("/student/**").hasAuthority("ROLE_USER")
                                .requestMatchers("/question/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/quiz/**").hasAuthority("ROLE_ADMIN"))
                .httpBasic(withDefaults());


        return http.build();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(customUserService);
    }


}