package at.fhv.itb.sem5.team6.libman.server.persistence;

import at.fhv.itb.sem5.team6.libman.server.model.Lending;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LendingRepository extends MongoRepository<Lending,String>{


}
