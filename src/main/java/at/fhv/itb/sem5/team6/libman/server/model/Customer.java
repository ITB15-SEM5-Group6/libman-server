package at.fhv.itb.sem5.team6.libman.server.model;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.mutable.MutableCustomer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

//Lombok annotations to reduce boilerplate (lombok plugin has to be installed in IDE)
@Data // applies lombok-annotations
@Getter // enables getters
@Setter // enables setters

//MongoDb annnotation
@Document
public class Customer implements MutableCustomer, Identifiable<String>, Serializable {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String iban;
    private String bic;
}
