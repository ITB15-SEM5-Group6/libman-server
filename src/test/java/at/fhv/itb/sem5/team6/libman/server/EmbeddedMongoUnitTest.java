package at.fhv.itb.sem5.team6.libman.server;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public abstract class EmbeddedMongoUnitTest {

    @Configuration
    @EnableMongoRepositories
    @ComponentScan(basePackages = {"at.fhv.itb.sem5.team6.libman.server"})
    static class FongoConfiguration extends AbstractMongoConfiguration {
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
}
