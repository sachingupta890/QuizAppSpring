package com.Quiz.quizapp.services;

import com.Quiz.quizapp.Aspects.TrackExecutionTime;
import com.Quiz.quizapp.dao.UserRepo;
import com.Quiz.quizapp.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean registration(UserModel user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

         UserModel newUser = userRepo.save(user);

         if(newUser != null){
             return true;
         }
         else{
             return false;
         }



    }
}
