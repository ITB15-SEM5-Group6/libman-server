package at.fhv.itb.sem5.team6.libman.server.model;

import at.fhv.itb.sem5.team6.libman.shared.enums.LendingState;
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
public class Lending implements Identifiable<String>, Serializable {

    @Id
    private String id;
    @DBRef //MongoDb uses this object as a reference
    private PhysicalMedia physicalMedia;
    @DBRef //MongoDb uses this object as a reference
    private Customer customer;
    private Date lendDate;
    private Integer extentions;
    private String info;
    private LendingState state;

}
