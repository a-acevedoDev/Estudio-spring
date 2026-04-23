package com.spring.springbootjpa;

import com.spring.springbootjpa.dto.PersonDto;
import com.spring.springbootjpa.entities.Person;
import com.spring.springbootjpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {
    // Se añade esta implementacion debido a que es una app de consola, no web.

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        subQueries();
    }

    @Transactional(readOnly = true)
    public void subQueries() {
        System.out.println("Sub consulta por el nombre mas corto y su largo");
        List<Object[]> list = personRepository.getShorterName();
        list.forEach(objects -> System.out.println("Nombre: " + objects[0] + " Largo: " + objects[1]));

        System.out.println("Sub consulta persona con el id mas alto");
        Optional<Person> optionalPerson = personRepository.getLastRegistration();
        optionalPerson.ifPresent(System.out::println);

        System.out.println("Consulta con WHERE IN");
        List<Person> personList = personRepository.getPersonById(Arrays.asList(1L, 3L, 8L, 10L));
        personList.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void queriesFunctionAggregation() {
        System.out.println("Funcion total personas");
        Long total = personRepository.totalPerson();
        System.out.println(total);

        System.out.println("Funcion min id");
        Long min = personRepository.minId();
        System.out.println(min);

        System.out.println("Funcion max id");
        Long max = personRepository.maxId();
        System.out.println(max);

        System.out.println("Largo del nombre");
        List<Object[]> list = personRepository.lengthNamePersons();
        list.forEach(objects -> System.out.println("Nombre: " + objects[0] + " Largo: " + objects[1]));

        System.out.println("Minimo largo de nombre");
        int minLenght = personRepository.minLenghtName();
        System.out.println(minLenght);

        System.out.println("Maximo largo de nombre");
        int maxLenght = personRepository.maxLenghtName();
        System.out.println(maxLenght);
    }


    @Transactional
    public void personalizedQueriesAndDefaultJpa() {
        System.out.println("Consulta todas las personas personalizada ORDER BY");
        List<Person> personList = personRepository.getAll();
        personList.forEach(System.out::println);

        System.out.println("Consulta todas las personas personalizada ORDER BY DESC");
        List<Person> personListDesc = personRepository.getAllNameLastnameDesc();
        personListDesc.forEach(System.out::println);

        System.out.println("Consulta todas las personas nombre ASC, apellido DESC por JPA");
        List<Person> persons= personRepository.findAllByOrderByNameAscLastnameDesc();
        persons.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void personalizedQueriesBetween() {
        System.out.println("Consulta entre ids con BETWEEN");
        List<Person> personList = personRepository.findAllByIdBetween(3L, 8L);
        personList.forEach(System.out::println);

        System.out.println("Consulta entre caracteres con BETWEEN");
        List<Person> personListCharacter = personRepository.findAllByNameBetween("A", "Q");
        personListCharacter.forEach(System.out::println);

        System.out.println("Consulta entre ids con BETWEEN de JPA");
        List<Person> personIdJpa = personRepository.findByIdBetween(4L, 9L);
        personIdJpa.forEach(System.out::println);

        System.out.println("Consulta entre caracteres con BETWEEN de JPA");
        List<Person> personNameJpa = personRepository.findByNameBetween("A", "Q");
        personNameJpa.forEach(System.out::println);

        System.out.println("Consulta entre caracteres con BETWEEN y ORDER BY");
        List<Person> personIdOrder = personRepository.findAllByIdBetweenOrderBy(3L, 8L);
        personIdOrder.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void personalizedQueriesConcatUpperAndLowerCase(){
        System.out.println("Consulta de nombre completo CONCAT()");
        List<String> fullName = personRepository.findAllFullNameConcat();
        fullName.forEach(System.out::println);

        System.out.println("Consulta de nombre completo en upper case UPPER(CONCAT())");
        List<String> fullNameUpper = personRepository.findAllFullNameConcatUpper();
        fullNameUpper.forEach(System.out::println);

        System.out.println("Consulta de nombre completo en lower case LOWER(CONCAT())");
        List<String> fullNameLower = personRepository.findAllFullNameConcatLower();
        fullNameLower.forEach(System.out::println);

        System.out.println("Consulta de persona con UPPER y LOWER case");
        List<Object[]> personUpperLower = personRepository.findAllPersonWithUpperAndLowerCase();
        personUpperLower.forEach(objects -> System.out.println(
                "Id: " + objects[0] +
                " Name: " + objects[1] +
                " Lastname: " + objects[2] +
                " Programming language: " + objects[3]));
    }

    @Transactional(readOnly = true)
    public void personalizedQueriesDistinct() {
        System.out.println("Consulta con nombres de personas");
        List<String> names = personRepository.findAllNames();
        names.forEach(System.out::println);

        System.out.println("Consulta con nombres de personas y DISTINCT");
        List<String> namesDis = personRepository.findAllNamesDistinct();
        namesDis.forEach(System.out::println);

        System.out.println("Consulta con lenguajes de porgramacion y DISTINCT");
        List<String> programmingLanguageDis = personRepository.findAllProgrammingLanguageDistinct();
        programmingLanguageDis.forEach(System.out::println);

        System.out.println("Consulta cuantos lenguajes de programacion hay y COUNT(DISTINCT)");
        Long count = personRepository.totalCountProgrammingLanguage();
        System.out.println(count);
    }

    @Transactional(readOnly = true)
    public void personalizedQueries2(){
        System.out.println("Consulta por objeto persona y lenguaje de programacion.");
        List<Object[]> personObj = personRepository.findAllMixPersonData();
        personObj.forEach(objects -> System.out.println("porgrammingLanguage: " + objects[1] + " person: " + objects[0]));

        System.out.println("Consulta que puebla y devuelve objeto entity de una instancia personalizada");
        List<Person> personList = personRepository.findAllPersonalizedObjectPerson();
        personList.forEach(System.out::println);

        System.out.println("Consulta que puebla y devuelve una clase Dto personalizada.");
        List<PersonDto> personDtos = personRepository.findAllPersonDto();
        personDtos.forEach(System.out::println);
    }

    @Transactional(readOnly = true) // Solo de consulta/lectura.
    public void personalizedQueries() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese id para el nombre de usuario:");
        Long id = scanner.nextLong();
        scanner.close();

        System.out.println("Mostrando nombre por id");
        String name = personRepository.getNameById(id);
        System.out.println(name);

        System.out.println("Mostrando nombre completo por id");
        String fullName = personRepository.getFullNameById(id);
        System.out.println(fullName);

        System.out.println("Consulta por campos personalziados por id");
        Object[] personReg = (Object[]) personRepository.obtenerPersonDataFullById(id);
        System.out.println("id= " + personReg[0] + " nombre= " + personReg[1] + " apellido= " + personReg[2] + " lenguaje= " + personReg[3]);

        System.out.println("Consulta personalizada lista");
        List<Object[]> regs = personRepository.obtenerPersonDataFull();
        regs.stream().forEach(p -> System.out.println("id= " + p[0] + " nombre= " + p[1] + " apellido= " + p[2] + " lenguaje= " + p[3]));

    }


    @Transactional
    public void create() {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        String lastname = scanner.next();
        String programmingLanguage = scanner.next();
        scanner.close();

        Person person = new Person(null, name, lastname, programmingLanguage);
        Person personNew = personRepository.save(person);
        System.out.println(personNew.getId() + " " + personNew.getName() + " " + personNew.getProgrammingLanguage());

    }

    @Transactional
    public void delete() {
        personRepository.findAll().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la id a eliminar: ");
        Long id = scanner.nextLong();

        Optional<Person> optionalPerson = personRepository.findById(id);

        optionalPerson.ifPresentOrElse(personRepository::delete,
                () -> System.out.println("No existe la persona"));

        personRepository.findAll().forEach(System.out::println);
        scanner.close();

    }

    @Transactional
    public void delete2() {
        personRepository.findAll().forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la id a eliminar: ");
        Long id = scanner.nextLong();
        personRepository.deleteById(id);
        personRepository.findAll().forEach(System.out::println);

    }

    @Transactional
    public void update() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la id a modificar:");
        Long id = scanner.nextLong();
        Optional<Person> optionalPerson = personRepository.findById(id);

        optionalPerson.ifPresent(person -> {
            System.out.println(person);
            System.out.println("Ingrese el nuevo lenguaje de programacion: ");
            String programmingLanguage = scanner.next();
            person.setProgrammingLanguage(programmingLanguage);
            Person personDb = personRepository.save(person);
            System.out.println(personDb);
        });
        scanner.close();
    }

    @Transactional(readOnly = true)
    public void findOne() {
//        Person person = null;
//        Optional<Person> optionalPerson = personRepository.findById(7L);
//        if (optionalPerson.isPresent()) {
//            person = optionalPerson.get();
//            System.out.println(person.getName() + " " + person.getLastname());
//        }
//        else {
//            System.out.println(person);
//        }

        // Version mas elegante con IFPRESENT, no confundir con ISPRESENT.
        // ifPresent solo si esta presente lo pasa como callback (expresion lambda).
        personRepository.findByNameContaining("Pepe").ifPresent(person -> System.out.println(person.getName() + " " + person.getLastname()));
    }

    @Transactional(readOnly = true)
    public void list() {
        List<Person> persons = (List<Person>) personRepository.findByProgrammingLanguageAndName("Java", "Andres");
        persons.stream().forEach( person -> System.out.println(person.getName() + " " + person.getProgrammingLanguage()));

        System.out.println(" ");

        List<Object[]> personsValue = personRepository.obtenerPersonData();
        personsValue.stream().forEach(objects -> System.out.println(objects[0] + " es experto en " + objects[1]));
    }
}
