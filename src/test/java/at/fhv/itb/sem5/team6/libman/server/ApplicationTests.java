package at.fhv.itb.sem5.team6.libman.server;

import at.fhv.itb.sem5.team6.libman.server.model.*;
import at.fhv.itb.sem5.team6.libman.server.persistence.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/*
 * in case of empty test failures, make sure that the package of spring application and spring test are equal!
 */

@RunWith(SpringRunner.class)
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
     * Should be in a comment
     */
    @Test
    public void insertionTest() {

        //Customers and Medias already exist


        //physicalMedias
        //insertTestDataPhysicalMedia(50);

        //reservations
        //insertTestDataReservation(50);
        
        //lendings
        //insertTestDataLending(50);

    }

    private void insertTestDataPhysicalMedia(int numberOfEntries) {

        List<PhysicalMedia> list = new ArrayList<>();
        //relation to media
        List<Media> m = medias.findAll();

        for(int i = 0; i < numberOfEntries; i++) {
            PhysicalMedia item = new PhysicalMedia();

            int randomMedia = r.nextInt(m.size());

            Availability availability = randomMedia% 2 == 0 ? Availability.AVAILABLE : Availability.NOT_AVAILABLE;
            Media media = m.get(randomMedia);

            item.setAvailability(availability);
            item.setMedia(media);

            list.add(item);
        }
        physicalMedias.save(list);
    }


    private void insertTestDataReservation(int numberOfEntries){

        List<Reservation> list = new ArrayList<>();

        //relation to customer and media
        List<Customer> c = customers.findAll();
        List<Media> m = medias.findAll();

        for(int i = 0; i < numberOfEntries; i++) {
            Reservation item = new Reservation();

            int randomCustomer = r.nextInt(c.size());
            int randomMedia = r.nextInt(m.size());

            item.setCustomer(c.get(randomCustomer));
            item.setMedia(m.get(randomMedia));

            list.add(item);
        }
        reservations.save(list);
    }

    private void insertTestDataLending(int numberOfEntries){

        List<Lending> list = new ArrayList<>();

        //relation to customer and physicalMedia
        List<Customer> c = customers.findAll();
        List<PhysicalMedia> p = physicalMedias.findAll();

        for(int i = 0; i < numberOfEntries; i++) {
            Lending item = new Lending();

            int randomCustomer = r.nextInt(c.size());
            int randomPhysicalMedia = r.nextInt(p.size());

            item.setCustomer(c.get(randomCustomer));
            item.setPhysicalMedia(p.get(randomPhysicalMedia));

            list.add(item);
        }

        lendings.save(list);
    }
}
