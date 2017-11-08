package at.fhv.itb.sem5.team6.libman.server.persistence;

import at.fhv.itb.sem5.team6.libman.server.model.Media;
import at.fhv.itb.sem5.team6.libman.shared.enums.Genre;
import at.fhv.itb.sem5.team6.libman.shared.enums.MediaType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MediaRepository extends MongoRepository<Media, String> {
    List<Media> findDistinctByTitleLikeOrAuthorLikeOrIsbnLikeOrPublisherLikeAllIgnoreCase(String title, String author, String isbn, String publisher);

}
