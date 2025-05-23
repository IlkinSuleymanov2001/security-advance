package com.example.demo.aspect.logger;

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
public class LoggerMethods {

    // Pointcut targeting all public methods in classes annotated with @LogElapsedTime
    @Pointcut("within(@com.example.demo.aspect.logger.Logger *) && execution(public * *(..))")
    public void loggerPointCut() {

    }

    @SneakyThrows
    @Around(value = "loggerPointCut()")
    public Object logger(ProceedingJoinPoint joinPoint) {

        log.info("starting %d method {}", joinPoint.getSignature());
        var response = joinPoint.proceed();
        log.info("finished %d method {}", joinPoint.getSignature());
        return response;
    }
}
