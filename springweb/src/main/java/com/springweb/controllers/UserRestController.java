package com.springweb.controllers;

import com.springweb.dto.UserDto;
import com.springweb.models.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
//RestController es la combinacion de Controller + ResponseBody(cuerpo de la respuesta, JSON).
@RequestMapping("/api")
//RequestMapping asigna una ruta predetermina como base, ej: "/api".
//Esto genera que tengamos rutas como /api/details, /api/profile, /api/control, etc. Todas dentro del mismo controlador.
public class UserRestController {

    @RequestMapping(path = "/details-map", method = RequestMethod.GET)
    //RequestMapping sin parametros es por defento un GetMapping, ambos pueden utilizarse.
    //Sin embargo, RequestMapping es personalizable, por ejemplo cambiar el metodo, ya sea GET, POST, PUT, DELETE, etc.
    public Map<String, Object> detailsMap() {
        //Para las response es obligatorio usar un Map(Java util). Sin embargo, no se entrega como parametro al metodo.
        User user = new User("Alexander", "Acevedo", "contacto@gmail.com");
        //Creamos usuario de clase User.
        Map<String, Object> body = new HashMap<>();
        body.put("title", "Hola mundo de Spring Boot");
        body.put("user", user);
        //Entregamos el objeto user al body, con key(clave JSON) "user".
        return body;
    }

    @GetMapping (path = "/details")
    public UserDto details() {
        //Metodo Data Transfer Object(DTO), para transferir datos o colar datos utilizando una clase POJO.
        UserDto userDto = new UserDto();
        User user = new User("Alexander", "Acevedo", "contacto@gmail.com");
        userDto.setUser(user);
        userDto.setTitle("Hola mundo Spring Boot, este es DTO");
        return userDto;
    }

    @GetMapping (path = "/list-users")
    public List<User> listUser() {
        //Metodo que lista los usuarios y los entrega en JSON.
        User user1 = new User("Alexander", "Acevedo", "contacto1@gmail.com");
        User user2 = new User("Gonzalo", "Toledo", "contacto2@gmail.com");
        User user3 = new User("Nicolas", "Guerra", "contacto3@gmail.com");

        //Opcion 1.- Crear lista y agregar objetos User a la lista normal.
        //List<User> users = new ArrayList<>();
        //users.add(user1);
        //users.add(user2);
        //users.add(user3);

        //Opcion 2.- Agregar objetos User a lista con Helper. Crea lista y se agregan inmediatamente.
        List<User> users = Arrays.asList(user1, user2, user3);
        return users;
    }
}
