package com.springweb.controllers;

import com.springweb.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("/details")
    public String details(Model model) {
        //Para el argumento se puede utilizar Model(Spring) o Map<>(Java util), ambos sirven para pasar datos a la vista.
        //Model model ---> .addAttribute
        //Map<String, Object> ---> .put
        //En este caso ambos son equivalentes.
        User user = new User("Alexander", "Acevedo", "contacto1@gmail.com");
        //Creamos usuario de clase User.
        model.addAttribute("title", "Hola mundo de Spring Boot")
                .addAttribute("user", user);
        return "details";
    }

    @GetMapping (path = "/list")
    public String list(ModelMap model) {
        //ModelMap == Map de Model, clase especial de spring que hereeda de Map al igual que Model.
        //List<User> users =
        //        Arrays.asList(
        //                new User("Alex", "Acevedo", "@gmail.com"),
        //                new User("Gonzalo", "Toledo", "@hotmail.com"),
        //                new User("Nicolas", "Guerra", "@outlook.com")
        //                );
        //model.addAttribute("users", users);
        model.addAttribute("title", "Lista de usuarios!");
        return "list";
    }

    @ModelAttribute ("users")
    //ModelAttribute guarda el valor en una variable, en este caso "users", la cual ES GLOBAL, ose autilizable en todas las vistas.
    //Annotation muy util cuando necesitamos reutilizar datos.
    public List<User> modelUsers() {
        return Arrays.asList(new User("Alex", "Acevedo", "@gmail.com"),
                                new User("Gonzalo", "Toledo", "@hotmail.com"),
                                new User("Nicolas", "Guerra", "@outlook.com"));
    }
}
