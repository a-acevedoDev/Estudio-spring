package com.spring.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(1)
// Indica el orden en que se ejecutan los AOP. Siempre es el primero en entrar, pero el ultimo en salir
@Aspect
@Component
public class GreetingFooAspect {

    private Logger logger = LoggerFactory.getLogger(GreetingFooAspect.class);

    @Before("GreetingServicePointcuts.greetingFooLoggerAspectPoinCut()")
    public void loggerBefore(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Antes foo: " + method + " invocado con los parametros  " + args);
    }

    @After("GreetingServicePointcuts.greetingFooLoggerAspectPoinCut()")
    public void loggerAfter(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues foo: " + method + " con los parametros" + args);
    }
}
