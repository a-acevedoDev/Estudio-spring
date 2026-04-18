package com.springweb.controllers;

import com.springweb.dto.ParamDto;
import com.springweb.dto.ParamMixDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Enviar parametros de tipo string query en la URL

@RestController
@RequestMapping ("/api/params")
//Ruta general de los metodos.
public class RequestParamsController {

    @GetMapping ("/query")
    public ParamDto query(@RequestParam (required = false, defaultValue = "no llego el mensaje") String message) {
        //RequestParam sirve para capturar datos que vienen en la URL (query string).
        //En este caso: ELDOMINIO.COM/api/params/query?message=aqui%20viene%20un%20mensaje
        //Atributos:
        //required: true (por defecto), false (el dato es opcional).
        //defaultValue = "Algun valor" (el valor que toma por defecto, solo sino se ingresa uno).
        //name = "unNombre" (el nombre con el cual queramos identificar en la URL, por defecto el nombre del parametro del metodo).
        ParamDto param = new ParamDto();
        param.setMessage(message);
        return  param;
    }

    @GetMapping ("/multiquery")
    public ParamMixDto multiquery(@RequestParam() String text,
                                  @RequestParam() Integer code) {
        ParamMixDto params = new ParamMixDto();
        params.setMessage(text);
        params.setCode(code);
        return params;
    }

    @GetMapping ("/request")
    public ParamMixDto request(HttpServletRequest request) {
        //HttpServletRequest sirve para inyectar parametros de forma nativa.
        //En caso de no encontrar el valor del parametro lo transforma en null, esto podria dar posibles errores de formato.
        //Ej: no ingresar un int y que este parseado (String a Int), al recibir un null en un Parse esto daria error de formato numerico.
        //Formato numerico == NumberFormatException
        //Podemos tratar el dato con un Try Catch para evitar estos casos.

        Integer code = 0;
        try {
            code = Integer.parseInt(request.getParameter("code"));
        } catch (NumberFormatException e) {
        }

        ParamMixDto params = new ParamMixDto();
        params.setCode(code);
        params.setMessage(request.getParameter("text"));
        return params;
    }
}
