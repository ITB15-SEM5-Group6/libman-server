package at.fhv.itb.sem5.team6.libman.server;

import at.fhv.itb.sem5.team6.libman.server.application.LibraryController;
import at.fhv.itb.sem5.team6.libman.server.application.mapper.*;
import at.fhv.itb.sem5.team6.libman.server.persistence.*;
import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public abstract class EmbeddedMongoUnitTest {

    @Autowired
    protected JmsTemplate jmsTemplate;

    @Autowired
    protected DaRulezRepository daRulezRepository;

    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CustomerMapper customerMapper;

    @Autowired
    protected LendingRepository lendingRepository;
    @Autowired
    protected LendingMapper lendingMapper;

    @Autowired
    protected MediaRepository mediaRepository;
    @Autowired
    protected MediaMapper mediaMapper;

    @Autowired
    protected PhysicalMediaRepository physicalMediaRepository;
    @Autowired
    protected PhysicalMediaMapper physicalMediaMapper;

    @Autowired
    protected ReservationRepository reservationRepository;
    @Autowired
    protected ReservationMapper reservationMapper;

    @Autowired
    protected LibraryController libraryController;

    protected void daRulez() {
        TestData.daRulez(daRulezRepository);
    }

    protected void customer() {
        TestData.customer(customerRepository);
    }

    protected void media() {
        TestData.media(mediaRepository);
    }

    protected void physicalMedia(int number) {
        TestData.physicalMedia(physicalMediaRepository, mediaRepository, number);
    }

    protected void lending(int number) {
        TestData.lending(lendingRepository, physicalMediaRepository, customerRepository, number);
    }

    protected void reservation(int number) {
        TestData.reservation(reservationRepository, mediaRepository, customerRepository, number);
    }

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
