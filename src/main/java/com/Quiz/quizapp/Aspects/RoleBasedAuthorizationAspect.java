package com.Quiz.quizapp.Aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RoleBasedAuthorizationAspect {

    @Before("@annotation(org.springframework.security.access.prepost.PreAuthorize) && execution(* com.Quiz.quizapp.controller.StudentController.*(..))")
    public void checkUserAccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            throw new AccessDeniedException("Access Denied");
        }
    }

    @Before("@annotation(org.springframework.security.access.prepost.PreAuthorize) && execution(* com.Quiz.quizapp.controller.QuestionController.*(..))")
    public void checkAdminAccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new AccessDeniedException("Access Denied");
        }
    }
    @Before("@annotation(org.springframework.security.access.prepost.PreAuthorize) && execution(* com.Quiz.quizapp.controller.QuizController.*(..))")
    public void checkAdminAccess2() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new AccessDeniedException("Access Denied");
        }
    }
    @Before("execution(* com.Quiz.quizapp.controller.UserController.*(..)) && !within(com.Quiz.quizapp.controller.UserController)")
    public void skipAuthenticationForUserController() {
        // This method does nothing, effectively skipping authentication
    }
}
