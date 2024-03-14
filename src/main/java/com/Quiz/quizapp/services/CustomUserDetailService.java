package com.Quiz.quizapp.services;

import com.Quiz.quizapp.dao.UserRepo;
import com.Quiz.quizapp.model.MyUserDetail;
import com.Quiz.quizapp.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder password;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = Optional.ofNullable(userRepo.findByUsername(username));

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));



        UserDetails temp = user.map(MyUserDetail::new).get();
        System.out.println(temp.getAuthorities());
        System.out.println(temp.getUsername());
        System.out.println(temp.getPassword());
        return temp;


    }


}
