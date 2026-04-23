package com.spring.springbootjpa.repositories;

import com.spring.springbootjpa.dto.PersonDto;
import com.spring.springbootjpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE p.id IN ?1")
    List<Person> getPersonById(List<Long> ids);

    @Query("SELECT p.name, LENGTH(p.name) FROM Person p WHERE LENGTH(p.name) = (SELECT MIN(LENGTH(p.name)) FROM Person p)")
    List<Object[]> getShorterName();

    @Query("SELECT p from Person p WHERE p.id=(SELECT MAX(p.id) FROM Person p)")
    Optional<Person> getLastRegistration();

    //------------------------------------------------------------------------------------------------------------------

    @Query("SELECT p.name, LENGTH(p.name) FROM Person p")
    List<Object[]> lengthNamePersons();

    @Query("SELECT MIN(LENGTH(p.name)) FROM Person p")
    int minLenghtName();

    @Query("SELECT MAX(LENGTH(p.name)) FROM Person p")
    int maxLenghtName();

    //------------------------------------------------------------------------------------------------------------------

    @Query("SELECT COUNT(p) FROM Person p")
    Long totalPerson();

    @Query("SELECT MIN(p.id) FROM Person p")
    Long minId();

    @Query("SELECT MAX(p.id) FROM Person p")
    Long maxId();

    //------------------------------------------------------------------------------------------------------------------

    @Query("SELECT p FROM Person p ORDER BY p.name")
    List<Person> getAll();

    @Query("SELECT p FROM Person p ORDER BY p.name, p.lastname DESC")
    List<Person> getAllNameLastnameDesc();

    List<Person> findAllByOrderByNameAscLastnameDesc();

    //------------------------------------------------------------------------------------------------------------------

    @Query("SELECT p FROM Person p WHERE  p.id BETWEEN ?1 AND ?2 ORDER BY p.name")
    List<Person> findAllByIdBetweenOrderBy(Long id1, Long id2);

    List<Person> findByNameBetween(String s1, String s2);

    List<Person> findByIdBetween(Long id1, Long id2);

    @Query("SELECT p FROM Person p WHERE  p.id BETWEEN ?1 AND ?2")
    List<Person> findAllByIdBetween(Long id1, Long id2);

    @Query("SELECT p FROM Person p WHERE  p.name BETWEEN ?1 AND ?2")
    List<Person> findAllByNameBetween(String name1, String name2);   // Es un rango donde el segundo caracter no se cuenta.

    //------------------------------------------------------------------------------------------------------------------

    @Query("SELECT LOWER(p.name || ' ' || p.lastname) FROM Person p")
    List<String> findAllFullNameConcatLower();

    @Query("SELECT UPPER(p.name || ' ' || p.lastname) FROM Person p")
    List<String> findAllFullNameConcatUpper();

    @Query("SELECT p.id, UPPER(p.name), LOWER(p.lastname), UPPER(p.programmingLanguage) FROM Person p")
    List<Object[]> findAllPersonWithUpperAndLowerCase();

    // Dos maneras de concadenar datos.
    // @Query("SELECT CONCAT(p.name, ' ', p.lastname) FROM Person p")
    @Query("SELECT p.name || ' ' || p.lastname FROM Person p")
    List<String> findAllFullNameConcat();

    @Query("SELECT COUNT(DISTINCT(p.programmingLanguage)) FROM Person p")
    Long totalCountProgrammingLanguage();

    @Query("SELECT DISTINCT(p.programmingLanguage) FROM Person p")
    List<String> findAllProgrammingLanguageDistinct();

    @Query("SELECT p.name FROM Person p")
    List<String> findAllNames();

    @Query("SELECT DISTINCT(p.name) FROM Person p")
    List<String> findAllNamesDistinct();

    //------------------------------------------------------------------------------------------------------------------

    // En este metodo como devuelvo otra clase (un Dto en este caso) necesito especificar la ruta de donde se encuentra esa clase.
    @Query("SELECT new com.spring.springbootjpa.dto.PersonDto(p.name, p.lastname) FROM Person p")
    List<PersonDto> findAllPersonDto();

    //------------------------------------------------------------------------------------------------------------------

    // Metodo para instaciar solo Person(name, lastname), instanciamos nosotros la clase Person en la querry,
    // debemos tener un constructor especifico para esto con los parametros que necesitamos.
    @Query("SELECT new Person(p.name, p.lastname) FROM Person p")
    List<Person> findAllPersonalizedObjectPerson();

    // Entrega el objeto completo "p" y luego solo un atributo "p.etc", estos seran accedibles mediante el indice "[]".
    @Query("SELECT p, p.programmingLanguage FROM Person p")
    List<Object[]> findAllMixPersonData();

    //------------------------------------------------------------------------------------------------------------------

    @Query("SELECT p.name FROM Person p WHERE p.id=?1")
    String getNameById(Long id);

    @Query("SELECT CONCAT(p.name, ' ', p.lastname) FROM Person p WHERE p.id=?1")
    String getFullNameById(Long id);

    //------------------------------------------------------------------------------------------------------------------

    // Metodo personalizado que imita el "findById()" de JPA.
    @Query("SELECT p FROM Person p WHERE p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("SELECT p FROM Person p WHERE p.name=?1")
    Optional<Person> findOneName(String name);

    @Query("SELECT p FROM Person p WHERE p.name LIKE %?1%")
    Optional<Person> findOneLikeName(String name);

    // Equivalente al metodo "findOneLikeName" de JPA.
    Optional<Person> findByNameContaining(String name);

    //------------------------------------------------------------------------------------------------------------------

    // Los siguientes metodos hacen exactamente lo mismo, sin embargo:
    // el primero se autoimplementa utilizando la nomenclatura de JPA findBy + atributo1 + And + atributo2.
    // el segundo al no respetar la nomenclatura no se autoimplementa, auqnue realiza la misma accion por la consulta JPA con @Query.

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("SELECT p from Person p WHERE p.programmingLanguage=?1 AND p.name=?2")    // Esta anotacion ejecuta la query de tipo JPA.
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);

    //------------------------------------------------------------------------------------------------------------------

    // Sobre carga de metodo: muchos metodos con el mismo nombre, pero con distintos parametros, se ejecuta el que se ajusta sus parametros.
    @Query("SELECT p.name, p.programmingLanguage FROM Person p")
    List<Object[]> obtenerPersonData();

    @Query("SELECT p.id, p.name, p.lastname, p.programmingLanguage FROM Person p")
    List<Object[]> obtenerPersonDataFull();

    @Query("SELECT p.id, p.name, p.lastname, p.programmingLanguage FROM Person p WHERE p.id=?1")
    Object obtenerPersonDataFullById(Long id);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.name=?1")
    List<Object[]> obtenerPersonData(String name);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.name=?1 AND p.programmingLanguage=?2")
    List<Object[]> obtenerPersonData(String name, String programmingLanguage);
}
