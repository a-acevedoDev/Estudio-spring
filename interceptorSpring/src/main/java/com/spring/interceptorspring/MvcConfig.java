package com.spring.interceptorspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier("timeInterceptor")
    private HandlerInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Incluye las rutas del interceptor, solo funcionan las que se agreguen.
        //registry.addInterceptor(timeInterceptor).addPathPatterns("/app/foo", "/app/var");
        // El "/app/**" incluye todas las rutas dentro de app, y "/**" son todas las rutas de la aplicacion.
        registry.addInterceptor(timeInterceptor).addPathPatterns("/app/**");

        // Excluye las rutas de interceptor, funcionan todas menos las agregadas.
        //registry.addInterceptor(timeInterceptor).excludePathPatterns("/app/foo", "/app/var");
    }
}
