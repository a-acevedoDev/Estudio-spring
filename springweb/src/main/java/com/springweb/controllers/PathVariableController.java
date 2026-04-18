package com.springweb.controllers;

import com.springweb.dto.ParamDto;
import com.springweb.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping ("/api/var")
public class PathVariableController {

    //@Value es una anotacion para sirve para inyectar valores en campos, metodos o parametros de constructores.
    @Value("${config.userName}")
    private String userName;

    @Value("${config.code}")
    private Integer code;

    @Value("${config.message}")
    private String message;

    @Value("${config.listOfValues}")
    private String[] listOfValues;

    @Value("${config.list}")
    private List<String> list;

    @Value("#{ '${config.listOfValues}'.split(',')}")
    //Con #{ } hacemos que el interior sea tratado como expresion String, por ende podemos usar metodos.
    //En este caso el .split(',') para hacer un arreglo con las ','. Recordar siempre usar comillas simples ''.
    private List<String> listExpresion;

    @Value("#{ '${config.listOfValues}'.toUpperCase()}")
    //Tambien podemos usarlo como un String simple sin usar metodos o con metodos de String como uppercase, lowercase, etc.
    private String valueString;

    @Value("#{${config.valuesMap}}")
    //Recordar el #{ } cuando queremos que el interior sea una expresion.
    private Map<String, Object> valuesMap;

    @Value("#{${config.valuesMap}.product}")
    //Al ser una expresion podemos directamente consultar por el valor de una determina key del Map.
    private String product;

    @Value("#{${config.valuesMap}.price}")
    //Mismo caso pero con el precio "price".
    private Long price;

    @Autowired
    private Environment environment;

    @GetMapping ("/bar/{message}")
    //@GetMapping para solicitar/obtener.
    public ParamDto path(@PathVariable String message) {
        //@PathVariable nos ayuda a identificar recursos especificos de la ruta URL.

        //@GetMapping ("/api/datos/{nombre}")
        //(@PathVariable (name="nombre") String otroNombre)
        //A diferencia del @RequestParam no retorna un string query y ademas, solo tiene el atributo "name",
        //este sirve en caso de que el nombre en la URL no coincida con el nombre del parametro en la funcion.

        //Ejemplo de URL con @PathVariable: DOMINIO.COM/api/datos/Alexander

        ParamDto paramDto = new ParamDto();
        paramDto.setMessage(message);
        return paramDto;
    }

    @GetMapping ("/mix/{id}/{product}")
    public Map<String, Object> pathMix(@PathVariable Long id,
                                       @PathVariable String product) {
        //Se puede utilizar un Map<> con @PathVariable para crear rutas URL variables.

        Map<String, Object> json = new HashMap<>();
        json.put("id", id);
        json.put("product", product);
        return json;
    }
    @PostMapping ("/create")
    //@PostMapping para enviar.
    public User create(@RequestBody User user) {
        //Metodo para enviar request.

        //Aqui podria tratar la informacion del JSON antes de enviar.

        return user;
    }

    @GetMapping("/values")
    public Map<String, Object> values(@Value("${config.internal}") String internal) {
        //Metodo Get con @Value externo e interno.
        Map<String, Object> json = new HashMap<>();
        json.put("username", userName);
        json.put("code", code);
        json.put("code2", environment.getProperty("config.code", Long.class));
        //json.put("code2", Integer.valueOf(environment.getProperty("config.code"))); esto transofrma el String a Integer. Pero...
        //getProperty puede recibir solo el dato " ", pero tambien puede recibir el dato " " y la clase a convertir
        json.put("message", message);
        json.put("message2", environment.getProperty("config.message"));
        json.put("listofvalues", listOfValues);
        json.put("internal", internal);
        json.put("list", list);
        json.put("listexpresion", listExpresion);
        json.put("valueString", valueString);
        json.put("valueMap", valuesMap);
        json.put("product", product);
        json.put("price", price);
        return json;
    }
}
