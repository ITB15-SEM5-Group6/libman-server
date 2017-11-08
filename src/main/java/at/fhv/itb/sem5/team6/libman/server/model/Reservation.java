package at.fhv.itb.sem5.team6.libman.server.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

//Lombok annotations to reduce boilerplate (lombok plugin has to be installed in IDE)
@Data // applies lombok-annotations
@Getter // enables getters
@Setter // enables setters

//MongoDb annnotation
@Document
public class Reservation implements Serializable {
    @Id
    private String id;
    @DBRef //MongoDb uses this object as a reference
    private Media media;
    @DBRef //MongoDb uses this object as a reference
    private Customer customer;
    private Date date;
}
