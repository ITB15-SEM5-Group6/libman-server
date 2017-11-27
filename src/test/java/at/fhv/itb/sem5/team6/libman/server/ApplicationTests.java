package at.fhv.itb.sem5.team6.libman.server;

import at.fhv.itb.sem5.team6.libman.server.persistence.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

import static at.fhv.itb.sem5.team6.libman.server.TestData.*;

/*
 * in case of empty test failures, make sure that the package of spring application and spring test are equal!
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTests {

    private Random r = new Random();

    @Autowired
    private DaRulezRepository daRulezRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private PhysicalMediaRepository physicalMediaRepository;

    @Autowired
    private LendingRepository lendingRepository;

    /**
     * General Process:
     * Functions, which makes it easier to insert testdata
     * InsertionTest: Insert data (Customers, Media, PhysicalMedias, Reservations, Lendings)
     * use annotation @Ignore to skip a test
     */
    @Test
    public void reinsertDatabase() {
        reinsertDatabase(3);
    }

    private void reinsertDatabase(int numberOfRandomRecords) {
        daRulez(daRulezRepository);
        media(mediaRepository);
        customer(customerRepository);
        physicalMedia(physicalMediaRepository, mediaRepository, numberOfRandomRecords);
        lending(lendingRepository, physicalMediaRepository, customerRepository, numberOfRandomRecords);
        reservation(reservationRepository, mediaRepository, customerRepository, numberOfRandomRecords);
    }
}
