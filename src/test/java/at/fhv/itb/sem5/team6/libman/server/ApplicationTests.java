package at.fhv.itb.sem5.team6.libman.server;

import at.fhv.itb.sem5.team6.libman.server.model.Customer;
import at.fhv.itb.sem5.team6.libman.server.model.Media;
import at.fhv.itb.sem5.team6.libman.server.model.Reservation;
import at.fhv.itb.sem5.team6.libman.server.persistence.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;
import java.util.stream.Collectors;

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


    @Test
    @Ignore
    public void contextLoads() {
        Customer customer = customers.findByLastName("Dengg").get(0);
        Media media = medias.findAll().stream().filter(media1 -> media1.getTitle().contains("Feuerkelch")).collect(Collectors.toList()).get(0);

        Reservation reservation = new Reservation();
        reservations.save(reservation);

    }

    /**
     * General Process:
     * Functions, which makes it easier to insert testdata
     * InsertionTest: Insert data (Customers, Media, PhysicalMedias, Reservations, Lendings)
     * use annotation @Ignore to skip a test
     */
    @Test
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
