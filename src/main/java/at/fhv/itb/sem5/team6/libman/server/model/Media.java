package at.fhv.itb.sem5.team6.libman.server.model;

import at.fhv.itb.sem5.team6.libman.shared.DTOs.mutable.MutableMedia;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

//Lombok annotations to reduce boilerplate (lombok plugin has to be installed in IDE)
@Data // applies lombok-annotations
@Getter // enables getters
@Setter // enables setters

//MongoDb annnotation
@Document
public class Media implements MutableMedia, Identifiable<String>, Serializable {
    @Id
    private String id;
    private String title;
    private MediaType type;
    private String isbn;
    private String author;
    private String publisher;
    private Date releaseDate;
    private String tags;
    private String Genre;


}
