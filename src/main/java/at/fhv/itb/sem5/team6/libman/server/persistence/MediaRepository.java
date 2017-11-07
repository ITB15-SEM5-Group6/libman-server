package at.fhv.itb.sem5.team6.libman.server.persistence;

import at.fhv.itb.sem5.team6.libman.server.model.Media;
import at.fhv.itb.sem5.team6.libman.server.model.MediaType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MediaRepository extends MongoRepository<Media, String> {
    List<Media> findDistinctByTypeEquals(MediaType type);

    List<Media> findDistinctByIdLikeOrTitleLikeAllIgnoreCase(String id, String title);

    List<Media> findDistinctByIdLikeOrTitleLikeOrTypeLikeAllIgnoreCase(String id, String title, MediaType type);
}
