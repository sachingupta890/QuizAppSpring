package com.Quiz.quizapp.Aspects;

import com.Quiz.quizapp.model.UserModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BcrypterAdvice {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Around("@annotation(com.Quiz.quizapp.Aspects.Bcrypt)")
    public Object bcryptPassword(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs(); // Get method arguments
        for (Object arg : args) {
            if (arg instanceof UserModel) {
                UserModel user = (UserModel) arg;
                String encryptedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(encryptedPassword);
            }
        }
        return joinPoint.proceed(); // Proceed with the method execution
    }
}

