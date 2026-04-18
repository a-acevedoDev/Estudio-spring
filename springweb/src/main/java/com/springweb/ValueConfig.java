package com.springweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:values.properties", encoding = "UTF-8")
        //@PropertySource("classpath:values2.properties"),
        //@PropertySource("classpath:values3.properties") recordar separar por comas ",".
})
//@PropertySources({ }), nos permite ingresar mas de un @PropertySource.
//@PropertySource("classpath:xxxxxx.properties") nos sirve para que Spring reconozca nuestros archivos properties
//y podamos hacer inyecciones. Solo viene por defecto el application.properties
//Podemos agregar los atributos "value=nombre.properties" y "encoding= "UTF-8"" para asignar el tipo de teclado y caracteres especiales.

public class ValueConfig {
}
