package at.fhv.itb.sem5.team6.libman.server.persistence;

import at.fhv.itb.sem5.team6.libman.server.model.Lending;
import at.fhv.itb.sem5.team6.libman.server.model.PhysicalMedia;
import at.fhv.itb.sem5.team6.libman.shared.enums.LendingState;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LendingRepository extends MongoRepository<Lending,String>{
    List<Lending> findDistinctByPhysicalMediaEqualsAndStateEquals(PhysicalMedia physicalMedia, LendingState state);
}
