package at.fhv.itb.sem5.team6.libman.server.model;

import at.fhv.itb.sem5.team6.libman.shared.enums.Genre;
import at.fhv.itb.sem5.team6.libman.shared.enums.MediaType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Getter
@Setter
public class Media {
    @Id
    private String id;
    private String title;
    private MediaType type;
    private String isbn;
    private String author;
    private String publisher;
    private Date releaseDate;
    private String tags;
    private Genre genre;
}
