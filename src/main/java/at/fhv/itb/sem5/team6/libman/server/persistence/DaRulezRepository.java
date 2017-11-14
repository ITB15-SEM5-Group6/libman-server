package at.fhv.itb.sem5.team6.libman.server.persistence;

import at.fhv.itb.sem5.team6.libman.server.model.DaRulez;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DaRulezRepository extends MongoRepository<DaRulez, String> {
    DaRulez findFirstBy();
}
