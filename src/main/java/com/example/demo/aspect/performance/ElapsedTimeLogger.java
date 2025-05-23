package com.example.demo.aspect.performance;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class ElapsedTimeLogger {


    // Pointcut targeting all public methods in classes annotated with @LogElapsedTime
    @Pointcut("within(@com.example.demo.aspect.performance.LogElapsedTime *) && execution(public * *(..))")
    public void elapsedTimePointCut() {

    }


    @SneakyThrows
    @Around(value = "elapsedTimePointCut()")
    public Object logElapsedTime(ProceedingJoinPoint joinPoint) {

        var startTime = System.currentTimeMillis();
        var response = joinPoint.proceed();
        var elapsedTime = System.currentTimeMillis() - startTime;
        log.info("Elapsed time for method {}: {} ms", joinPoint.getSignature(), elapsedTime);
        return response;
    }
}
