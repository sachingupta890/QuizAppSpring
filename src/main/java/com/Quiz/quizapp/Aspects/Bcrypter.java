package com.Quiz.quizapp.Aspects;

import com.Quiz.quizapp.model.UserModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Bcrypter {

    @Autowired
    PasswordEncoder passwordEncoder;


    @Before("@annotation(com.Quiz.quizapp.Aspects.TrackExecutionTime)")
    public Object profile(ProceedingJoinPoint pjp, UserModel user) throws Throwable {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        Object output = pjp.proceed();
        return output;
    }
}
