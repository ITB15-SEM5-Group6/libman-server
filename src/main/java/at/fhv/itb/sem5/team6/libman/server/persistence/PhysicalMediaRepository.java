package at.fhv.itb.sem5.team6.libman.server.persistence;
import at.fhv.itb.sem5.team6.libman.server.model.Availability;
import at.fhv.itb.sem5.team6.libman.server.model.Media;
import at.fhv.itb.sem5.team6.libman.server.model.PhysicalMedia;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PhysicalMediaRepository extends MongoRepository<PhysicalMedia, String> {

    List<PhysicalMedia> findDistinctByAvailabilityEquals(Availability availability);

    List<PhysicalMedia> findDistinctByMediaEquals(Media media);
}