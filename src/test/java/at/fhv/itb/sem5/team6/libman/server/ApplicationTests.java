package at.fhv.itb.sem5.team6.libman.server;

import at.fhv.itb.sem5.team6.libman.server.model.DaRulez;
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
    private ReservationRepository reservations;

    @Autowired
    private CustomerRepository customers;

    @Autowired
    private MediaRepository medias;

    @Autowired
    private PhysicalMediaRepository physicalMedias;

    @Autowired
    private LendingRepository lendings;

    @Autowired
    private DaRulezRepository daRulezRepository;

    @Test
    @Ignore
    public void addDaRulez() {
        daRulezRepository.deleteAll();

        DaRulez daRulez = new DaRulez();
        daRulez.setMaxExtensions(2);
        long maxLendingDuration = 14 * 24 * 60 * 60 * 1000;
        daRulez.setMaxLendingDurationInMilliseconds(maxLendingDuration);
        long maxReservationDuration = 7 * 24 * 60 * 60 * 1000;
        daRulez.setMaxReservationDuration(maxReservationDuration);
        daRulez.setAnnualFee(25.00f);
        daRulez.setOverdueFine(10.00f);

        daRulezRepository.save(daRulez);
    }

    /**
     * General Process:
     * Functions, which makes it easier to insert testdata
     * InsertionTest: Insert data (Customers, Media, PhysicalMedias, Reservations, Lendings)
     * use annotation @Ignore to skip a test
     */
    @Test
    @Ignore
    public void reinsertDatabase() {
        reinsertDatabase(50);
    }

    private void reinsertDatabase(int numberOfRandomRecords) {
        media(medias);
        customer(customers);
        physicalMedia(physicalMedias, medias, numberOfRandomRecords);
        lending(lendings, physicalMedias, customers, numberOfRandomRecords);
        reservation(reservations, medias, customers, numberOfRandomRecords);
    }
}
