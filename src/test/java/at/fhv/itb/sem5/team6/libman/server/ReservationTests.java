package at.fhv.itb.sem5.team6.libman.server;

import at.fhv.itb.sem5.team6.libman.server.model.Customer;
import at.fhv.itb.sem5.team6.libman.server.model.PhysicalMedia;
import at.fhv.itb.sem5.team6.libman.server.model.Reservation;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.ReservationDTO;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ReservationTests extends EmbeddedMongoUnitTest {

    @Before
    public void setUp() {
        media();
        customer();
    }

    @After
    public void tearDown() {
        mediaRepository.deleteAll();
        customerRepository.deleteAll();
        reservationRepository.deleteAll();
    }

    @Test
    public void simpleReservationTest() {

        MediaDTO mediaDTO = mediaMapper.toDTO(mediaRepository.findAll().get(0));

        CustomerDTO customerDTO = customerMapper.toDTO(customerRepository.findAll().get(0));

        ReservationDTO reservationDTO = libraryController.reserveMedia(mediaDTO, customerDTO);

        assertNotNull(reservationDTO);

        assertEquals(1, reservationRepository.count());

        Reservation reservation = reservationMapper.toModel(reservationDTO);

        assertEquals(reservation, reservationRepository.findAll().get(0));
    }

    @Test
    public void orderOfReservationTest() {
        reservation(50);

        List<ReservationDTO> actual = libraryController.findReservations();

        List<Reservation> reservationList = reservationRepository.findAll().stream().sorted(Comparator.comparing(Reservation::getDate)).collect(Collectors.toList());
        assertArrayEquals(reservationList.toArray(), reservationMapper.toModels(actual).toArray());
    }

    @Test
    public void cancelReservationTest() {
        physicalMedia(1);
        PhysicalMedia item = physicalMediaRepository.findAll().get(0);
        item.setAvailability(Availability.NOT_AVAILABLE);
        physicalMediaRepository.save(item);

        ReservationDTO reservation = libraryController.reserveMedia(mediaMapper.toDTO(item.getMedia()), customerMapper.toDTO(customerRepository.findAll().get(0)));

        assertEquals(1, reservationRepository.count());

        libraryController.cancelReservation(reservation);

        assertEquals(0, reservationRepository.count());
    }

    @Test
    public void isReservableTest() {
        physicalMedia(1);

        PhysicalMedia item = physicalMediaRepository.findAll().get(0);
        item.setAvailability(Availability.NOT_AVAILABLE);
        physicalMediaRepository.save(item);

        List<Customer> customers = customerRepository.findAll();
        Customer randomCustomer = customers.get(0);
        Customer randomCustomer2 = customers.get(1);

        assertNotEquals(randomCustomer, randomCustomer2);

        libraryController.reserveMedia(mediaMapper.toDTO(item.getMedia()), customerMapper.toDTO(randomCustomer));

        item.setAvailability(Availability.AVAILABLE);
        physicalMediaRepository.save(item);

        libraryController.reserveMedia(mediaMapper.toDTO(item.getMedia()), customerMapper.toDTO(randomCustomer2));
    }

    @Test(expected = IllegalStateException.class)
    public void doubleReservationTest() throws InterruptedException {
        MediaDTO mediaDTO = mediaMapper.toDTO(mediaRepository.findAll().get(0));

        CustomerDTO customerDTO = customerMapper.toDTO(customerRepository.findAll().get(0));

        libraryController.reserveMedia(mediaDTO, customerDTO);

        Thread.sleep(1000);

        //should not work
        libraryController.reserveMedia(mediaDTO, customerDTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullMediaTest() {
        // should not work
        libraryController.reserveMedia(null, customerMapper.toDTO(customerRepository.findAll().get(0)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullCustomerTest() {
        // should not work
        libraryController.reserveMedia(mediaMapper.toDTO(mediaRepository.findAll().get(0)), null);
    }

    @Test(expected = IllegalStateException.class)
    public void isNotReservableTest() {
        physicalMedia(1);

        PhysicalMedia item = physicalMediaRepository.findAll().get(0);
        item.setAvailability(Availability.AVAILABLE);
        physicalMediaRepository.save(item);

        Customer randomCustomer = customerRepository.findAll().get(0);
        libraryController.reserveMedia(mediaMapper.toDTO(item.getMedia()), customerMapper.toDTO(randomCustomer));
    }
}
