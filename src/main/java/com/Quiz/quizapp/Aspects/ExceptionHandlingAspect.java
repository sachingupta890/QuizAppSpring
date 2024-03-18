package com.Quiz.quizapp.Aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {
    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlingAspect.class);

    @Pointcut("execution(public * com.Quiz.quizapp.services.QuestionService.*(..))")
    public void serviceMethods() {}

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void afterReturningAdvice(Object result) {
        if (result instanceof ResponseEntity) {
            ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
            if (!responseEntity.getStatusCode().is2xxSuccessful()) {
                logger.warn("Returned ResponseEntity with non-successful status: {}", responseEntity.getStatusCode());
            } else {
                logger.info("Returned ResponseEntity with successful status: {}", responseEntity.getStatusCode());
            }
        }
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void afterThrowingAdvice(Exception exception) {
        logger.error("Exception occurred: {}", exception.getMessage(), exception);
    }
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void afterThrowingAdvice(RuntimeException ex) {
        logger.error("Exception occurred: {}", ex.getMessage(), ex);
    }
}
