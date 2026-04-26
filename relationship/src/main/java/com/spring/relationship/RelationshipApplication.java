package com.spring.relationship;

import com.spring.relationship.entities.*;
import com.spring.relationship.repositories.*;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootApplication
public class RelationshipApplication implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public static void main(String[] args) {
        SpringApplication.run(RelationshipApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        manyToManyBidirectionalRemove();
    }

    //------------------------------------------------------------------------------------------------------------------
    // @ManyToMany

    @Transactional
    public  void manyToManyBidirectionalRemove() {
        Student student1 = new Student("Pancho", "Panchon");
        Student student2 = new Student("David", "Racis");

        Course course1 = new Course("Typescritp", "Andres");
        Course course2 = new Course("Kubernetes", "Tulio");

        student1.addCourses(course1);
        student1.addCourses(course2);
        student2.addCourses(course2);
        studentRepository.saveAll(Set.of(student1, student2));

        System.out.println(student1);
        System.out.println(student2);

        Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
        studentOptionalDb.ifPresent(student -> {
            Optional<Course> optionalCourse = courseRepository.findById(3L);
            optionalCourse.ifPresent(course -> {
                student.getCourses().remove(course);
                studentRepository.save(student);
                System.out.println(student);
            });
        });

    }

    @Transactional
    public  void manyToManyBidirectional() {
        Student student1 = new Student("Pancho", "Panchon");
        Student student2 = new Student("David", "Racis");

        Course course1 = new Course("Typescritp", "Andres");
        Course course2 = new Course("Kubernetes", "Tulio");

        student1.addCourses(course1);
        student1.addCourses(course2);
        student2.addCourses(course2);
        studentRepository.saveAll(Set.of(student1, student2));

        System.out.println(student1);
        System.out.println(student2);


    }

    @Transactional
    public  void manyToManyRemoveFind() {
        Optional<Student> optionalStudent1 = studentRepository.findById(1L);
        Optional<Student> optionalStudent2 = studentRepository.findById(2L);

        Course course1 = courseRepository.findById(1L).get();
        Course course2 = courseRepository.findById(2L).get();

        Student student1 = optionalStudent1.get();
        Student student2 = optionalStudent2.get();

        student1.setCourses(Set.of(course1,course2));
        student2.setCourses(Set.of(course2));
        studentRepository.saveAll(Set.of(student1, student2));

        Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
        studentOptionalDb.ifPresent(student -> {
            Optional<Course> optionalCourse = courseRepository.findById(2L);
            optionalCourse.ifPresent(course -> {
                student.getCourses().remove(course);
                studentRepository.save(student);
                System.out.println(student);
            });
        });
    }

    @Transactional
    public  void manyToManyFind() {
        Optional<Student> optionalStudent1 = studentRepository.findById(1L);
        Optional<Student> optionalStudent2 = studentRepository.findById(2L);

        Course course1 = courseRepository.findById(1L).get();
        Course course2 = courseRepository.findById(2L).get();

        Student student1 = optionalStudent1.get();
        Student student2 = optionalStudent2.get();

        student1.setCourses(Set.of(course1,course2));
        student2.setCourses(Set.of(course2));
        studentRepository.saveAll(Set.of(student1, student2));

        System.out.println(student1);
        System.out.println(student2);
    }

    @Transactional
    public  void manyToMany() {
        Student student1 = new Student("Alexander", "Acevedo");
        Student student2 = new Student("Freddy", "Turbina");

        Course course1 = new Course("Spring", "Andres");
        Course course2 = new Course("Jpa", "Juanin");

        student1.setCourses(Set.of(course1,course2));
        student2.setCourses(Set.of(course2));
        studentRepository.saveAll(Set.of(student1, student2));

        System.out.println(student1);
        System.out.println(student2);
    }

    //------------------------------------------------------------------------------------------------------------------
    // @OneToOne

    @Transactional
    public void oneToOneBidirectional() {
        Client client = new Client("Myrlene", "Tavie");

        ClientDetails clientDetails = new ClientDetails(true, 1000); // No es necesario un save debido a que se guarda en cascada.

        client.setClientDetails(clientDetails);
        clientDetails.setClient(client);

        clientRepository.save(client);
        System.out.println(client);
    }


    @Transactional
    public void oneToOneFindById() {
        ClientDetails clientDetails = new ClientDetails(true, 1000);
        clientDetailsRepository.save(clientDetails);

        Optional<Client> clientOptional = clientRepository.findOneWithAll(2L);
        clientOptional.ifPresent(client -> {
            client.setClientDetails(clientDetails);
            clientRepository.save(client);
            System.out.println(client);
        });
    }

    @Transactional
    public void oneToOne() {
        ClientDetails clientDetails = new ClientDetails(true, 1000);
        clientDetailsRepository.save(clientDetails);

        Client client = new Client("Myrlene", "Tavie");
        client.setClientDetails(clientDetails);
        clientRepository.save(client);
        System.out.println(client);
    }

    //------------------------------------------------------------------------------------------------------------------
    // @OneToMany o @ManyToOne

    @Transactional
    public void removeInvoiceBidirectionalById() {
        Optional<Client> clientOptional = clientRepository.findOne(1L);
        clientOptional.ifPresent(client -> {

            Invoice invoice1 = new Invoice("Compras de tintura", 1000L);
            Invoice invoice2 = new Invoice("Compras de la casa", 1500L);

            client.addInvoices(invoice1)
                    .addInvoices(invoice2);

            clientRepository.save(client);
            System.out.println(client);

            Optional<Client> clientOptionalDb = clientRepository.findOne(1L);
            clientOptionalDb.ifPresent(client1 -> {
                Optional<Invoice> optionalInvoice = invoiceRepository.findById(2L);
                optionalInvoice.ifPresent(invoice -> {
                    client1.removeInvoice(invoice);
                    clientRepository.save(client1);
                    System.out.println(client1);
                });
            });
        });
    }

    @Transactional
    public void oneToManyBidirectionalById() {
        Optional<Client> clientOptional = clientRepository.findOne(1L);
        clientOptional.ifPresent(client -> {

            Invoice invoice1 = new Invoice("Compras de tintura", 1000L);
            Invoice invoice2 = new Invoice("Compras de la casa", 1500L);

            client.addInvoices(invoice1)
                    .addInvoices(invoice2);

            clientRepository.save(client);
            System.out.println(client);
        });
    }

    @Transactional
    public void oneToManyBidirectional() {
        Client client = new Client("Myrlen", "Tavie");

        Invoice invoice1 = new Invoice("Compras de tintura", 1000L);
        Invoice invoice2 = new Invoice("Compras de la casa", 1500L);

        client.addInvoices(invoice1)
                .addInvoices(invoice2);

        clientRepository.save(client);
        System.out.println(client);
    }

    @Transactional
    public void removeAddressesFindById() {
        Optional<Client> clientOptional  = clientRepository.findById(2L);
        clientOptional.ifPresent(client -> {
            Address address1 = new Address("Alameda", 12345);
            Address address2 = new Address("General Bonilla", 98765);

            Set<Address> addressSet = new HashSet<>();
            addressSet.add(address1);
            addressSet.add(address2);
            client.setAddresses(addressSet);

            Client clientDb = clientRepository.save(client);
            System.out.println(clientDb);

            Optional<Client> optionalClient = clientRepository.findOneWithAddresses(2L);
                    optionalClient.ifPresent(c -> {
                        c.getAddresses().remove(address2);
                        clientRepository.save(c);
                        System.out.println(c);});
        });
    }

    @Transactional
    public void removeAddress(){

        // Estos criterios no son los mismos que en remove.
        Client client = new Client("Fran", "Vera");

        Address address1 = new Address("Los juncos", 1357);
        Address address2 = new Address("Floridas", 2468);

        client.getAddresses().add(address1);
        client.getAddresses().add(address2);

        // Tras persistir cambian a pesar de tener el mismo nombre, re requieren metodos hashcode y equals para identificarse por detras.
        clientRepository.save(client);
        System.out.println(client);

        // Los siguientes "client" y "address1" no son los mismos, solo se identifican por metodo hascode, equals.
        Optional<Client> clientOptional = clientRepository.findById(3L);
        clientOptional.ifPresent(client1 -> {
            client1.getAddresses().remove(address1);
            clientRepository.save(client1);
            System.out.println(client1);
        });
    }

    @Transactional
    public void oneToManyFindById() {
        Optional<Client> clientOptional  = clientRepository.findById(2L);
        clientOptional.ifPresent(client -> {
            Address address1 = new Address("Alameda", 12345);
            Address address2 = new Address("General Bonilla", 98765);

            Set<Address> addressSet = new HashSet<>();
            addressSet.add(address1);
            addressSet.add(address2);
            client.setAddresses(addressSet);

            Client clientDb = clientRepository.save(client);
            System.out.println(clientDb);
        });
    }

    public void manyToOneCreateClient(){

        Client client = new Client("John", "Doe");
        clientRepository.save(client);

        Invoice invoice = new Invoice("Compras de oficina", 2000L);
        invoice.setClient(client);
        Invoice invoiceDb = invoiceRepository.save(invoice);
        System.out.println(invoiceDb);

    }

    @Transactional
    public void manyToOneFindByIdClient(){

        Optional<Client> clientOptional = clientRepository.findById(2L);
        clientOptional.ifPresent(client -> {
            Invoice invoice = new Invoice("Compras de oficina", 2000L);
            invoice.setClient(client);
            Invoice invoiceDb = invoiceRepository.save(invoice);
            System.out.println(invoiceDb);
        });



    }
}
