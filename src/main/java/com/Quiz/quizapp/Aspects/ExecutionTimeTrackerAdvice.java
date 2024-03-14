package com.Quiz.quizapp.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

@Aspect
@Component
public class ExecutionTimeTrackerAdvice {

    Logger logger = LoggerFactory.getLogger(ExecutionTimeTrackerAdvice.class);

    @Around("@annotation(com.Quiz.quizapp.Aspects.TrackExecutionTime)")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        logger.info("Method Execution Start");
        Object output = pjp.proceed();
        logger.info("Method Execution Completed");
        long elapsedTime = System.currentTimeMillis() - start;
        logger.info(pjp.getSignature()+"\nMethod took time to execute ------>>>> "+elapsedTime+" ms");
        return output;
    }
}
