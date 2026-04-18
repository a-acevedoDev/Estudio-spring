package com.spring.interceptorspring.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Component ("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Una forma de obtener el metodo handler que usamos.
        HandlerMethod controller = ((HandlerMethod) handler);
        logger.info("LoadinfTimeInterceptor: pretHandle() entrando..." + controller.getMethod().getName());

        long start = System.currentTimeMillis();
        request.setAttribute("start", start);

        Random random = new Random();
        int delay = random.nextInt(500);
        Thread.sleep(delay);

//        Ejemplo manejo de error o logica pre metodo.
//        Map<String, String> json = new HashMap<>();
//        json.put("error", "no tienes acceso.");
//        json.put("date", new Date().toString());
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(json);
//        response.setContentType("application/json");
//        response.setStatus(403);
//        response.getWriter().write(jsonString);
//        return false;     Al retornar false para el proceso y nunca accede al metodo.
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        long end = System.currentTimeMillis();
        long start = (long) request.getAttribute("start");
        long result = end - start;
        logger.info("Tiempo transcurrido " + result + "milisegundos");
        logger.info("LoadinfTimeInterceptor: postHandle() saliendo..." + ((HandlerMethod) handler).getMethod().getName());
    }

}
