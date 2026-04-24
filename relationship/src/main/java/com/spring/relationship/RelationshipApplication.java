package com.spring.relationship;

import com.spring.relationship.entities.Address;
import com.spring.relationship.entities.Client;
import com.spring.relationship.entities.Invoice;
import com.spring.relationship.repositories.ClientRepository;
import com.spring.relationship.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@SpringBootApplication
public class RelationshipApplication implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public static void main(String[] args) {
        SpringApplication.run(RelationshipApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        oneToManyFindById();
    }

    @Transactional
    public void oneToManyFindById() {
        Optional<Client> clientOptional  = clientRepository.findById(2L);
        clientOptional.ifPresent(client -> {
            Address address1 = new Address("Alameda", 12345);
            Address address2 = new Address("General Bonilla", 98765);

            client.setAddresses(Arrays.asList(address1, address2));

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
