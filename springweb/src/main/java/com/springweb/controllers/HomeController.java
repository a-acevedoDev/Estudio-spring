package com.springweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"", "/", "/home"})
    public String home() {
        //Redireccion, devuelve a una ruta especificada o no, depende del redirect:
        //Esto sirve para cuando hacemos formularios, creamos, objetos, hacemos peticiones, etc y queremos devolvernos a la ruta original.
        //Reinicia el request.

        return "redirect:/details";
        //redirect:, cambia la URL, borra la peticion HTTP, refresca la pagina.
        //forward;. no cambia la URL, no borra la peticion HTTP, no refresca la pagina, puede despachar a otra accion del controlador.
    }
}
