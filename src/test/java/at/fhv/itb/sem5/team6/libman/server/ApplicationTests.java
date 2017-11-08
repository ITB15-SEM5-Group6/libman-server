package at.fhv.itb.sem5.team6.libman.server;

import at.fhv.itb.sem5.team6.libman.server.model.*;
import at.fhv.itb.sem5.team6.libman.server.persistence.*;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.Genre;
import at.fhv.itb.sem5.team6.libman.shared.enums.MediaType;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
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
    @Ignore
    public void insertionTest() {

        int numberOfRandomEntries = 50;

        // remove all medias
        medias.findAll().forEach(media -> medias.delete(media));

        // add medias
        Media media = new Media();
        media.setTitle("Harry Potter und der Stein der Weisen");
        media.setType(MediaType.CD);
        media.setIsbn("isbn");
        media.setAuthor("author");
        media.setPublisher("publisher");
        media.setReleaseDate(Date.valueOf("22.2.2011"));
        media.setTags("tags");
        media.setGenre(Genre.ACTION);

        medias.insert(media);

        // TODO: insert all medias with additional data

        //Media(title=Harry Potter und der Stein der Weisen, type=CD, isbn=null, author=null, publisher=null, releaseDate=null, tags=null, Genre=null)
        //Media(title=Harry Potter und die Kammer des Schreckens, type=BOOK, isbn=null, author=null, publisher=null, releaseDate=null, tags=null, Genre=null)
        //Media(title=Harry Potter und der Gefangene von Askaban, type=BOOK, isbn=null, author=null, publisher=null, releaseDate=null, tags=null, Genre=null)
        //Media(title=Harry Potter und der Feuerkelch, type=BOOK, isbn=null, author=null, publisher=null, releaseDate=null, tags=null, Genre=null)
        //Media(title=Harry Potter und der Orden des Phönix, type=BOOK, isbn=null, author=null, publisher=null, releaseDate=null, tags=null, Genre=null)
        //Media(title=Harry Potter und der Halbblutprinz, type=BOOK, isbn=null, author=null, publisher=null, releaseDate=null, tags=null, Genre=null)
        //Media(title=Harry Potter und die Heiligtümer des Todes, type=BOOK, isbn=null, author=null, publisher=null, releaseDate=null, tags=null, Genre=null)


        // remove all and add new random physicalMedias
        physicalMedias.findAll().forEach(physicalMedia -> physicalMedias.delete(physicalMedia));
        insertTestDataPhysicalMedia(numberOfRandomEntries);

        // remove all and add new random reservations
        reservations.findAll().forEach(reservation -> reservations.delete(reservation));
        insertTestDataReservation(numberOfRandomEntries);

        // remove all and add new random lendings
        lendings.findAll().forEach(lending -> lendings.delete(lending));
        insertTestDataLending(numberOfRandomEntries);

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
