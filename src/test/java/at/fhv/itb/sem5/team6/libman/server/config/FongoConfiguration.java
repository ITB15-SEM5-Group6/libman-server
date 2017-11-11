package at.fhv.itb.sem5.team6.libman.server.config;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(repositoryBaseClass = MongoRepository.class)
public class FongoConfiguration extends AbstractMongoConfiguration {
    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return "embedded_db";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new Fongo(getDatabaseName()).getMongo();
    }
}
