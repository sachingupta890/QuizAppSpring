package com.Quiz.quizapp.controller;

import com.Quiz.quizapp.model.UserModel;
import com.Quiz.quizapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.oauth2.resourceserver.OAuth2ResourceServerSecurityMarker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("register")
    public ResponseEntity<Map<String,String>> register(@RequestBody UserModel user){
        Map<String,String> res = new HashMap<>();
        boolean result = userService.registration(user);
        if(result){
            res.put("Success","true");
            res.put("message","User Created");
            return  ResponseEntity.ok(res);

        }
        else{
            res.put("Success","false");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }

    }
}
