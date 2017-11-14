package at.fhv.itb.sem5.team6.libman.server.persistence;

import at.fhv.itb.sem5.team6.libman.server.model.Media;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MediaRepository extends MongoRepository<Media, String> {
    List<Media> findDistinctByOrderByTitleAscTypeAsc();

    List<Media> findDistinctByTitleLikeOrAuthorLikeOrIsbnLikeOrPublisherLikeAllIgnoreCaseOrderByTitleAscTypeAsc(String title, String author, String isbn, String publisher);

}
