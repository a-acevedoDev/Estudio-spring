package com.spring.aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingServicePointcuts {
    // Esta clase sirve para desacoplar y tener orden con nuestros Pointcut.
    // NOTA: En esta clase deberan ser siempre metodos public, debido a que se necesita acceder a ellos desde fuera,
    // sin embargo, si estan en una clase propia deberan ser privados.

    @Pointcut("execution(* com.spring.aop.services.GreetingService.*(..))")
    public void greetingLoggerPointCut() {}
    // Sirve para designar un pointcut para los interceptores, evita repetir la ruta y metodos de intercepcion. Esto nos permite tener un codigo mas limpio y reutilizable.

    @Pointcut("execution(* com.spring.aop.services.GreetingService.*(..))")
    public void greetingFooLoggerAspectPoinCut() {}
}
