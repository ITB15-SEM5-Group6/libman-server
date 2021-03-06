package at.fhv.itb.sem5.team6.libman.server.persistence;
import at.fhv.itb.sem5.team6.libman.server.model.Media;
import at.fhv.itb.sem5.team6.libman.server.model.PhysicalMedia;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PhysicalMediaRepository extends MongoRepository<PhysicalMedia, String> {
    List<PhysicalMedia> findDistinctByOrderByIndexAsc();

    List<PhysicalMedia> findDistinctByMediaEqualsOrderByIndexAsc(Media media);

    List<PhysicalMedia> findDistinctByMediaEqualsAndAvailabilityEqualsOrderByIndexAsc(Media media, Availability availability);
}
