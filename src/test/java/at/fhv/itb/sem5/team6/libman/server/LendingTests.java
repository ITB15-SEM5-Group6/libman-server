package at.fhv.itb.sem5.team6.libman.server;

import at.fhv.itb.sem5.team6.libman.server.model.*;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.LendingDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.LendingState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class LendingTests extends EmbeddedMongoUnitTest {

    @Before
    public void setUp() {
        daRulez();
        customer();
        media();
        physicalMedia(1);
    }

    @After
    public void tearDown() {
        customerRepository.deleteAll();
        mediaRepository.deleteAll();
        physicalMediaRepository.deleteAll();
        lendingRepository.deleteAll();
        reservationRepository.deleteAll();
    }

    @Test
    public void simpleLendingTest() {
        CustomerDTO customer = customerMapper.toDTO(customerRepository.findAll().get(0));
        PhysicalMediaDTO physicalMedia = physicalMediaMapper.toDTO(physicalMediaRepository.findAll().get(0));

        LendingDTO lendingDTO = libraryController.lend(physicalMedia.getId(), customer.getId());

        assertNotNull(lendingDTO);

        Lending lending = lendingMapper.toModel(lendingDTO);
        assertEquals(lending, lendingRepository.findOne(lending.getId()));
    }

    @Test
    public void reservedByYouLendingTest() {
        Customer customer = customerRepository.findAll().get(0);
        PhysicalMedia physicalMedia = physicalMediaRepository.findAll().get(0);
        Media media = physicalMedia.getMedia();

        Reservation reservation = new Reservation();
        reservation.setMedia(media);
        reservation.setCustomer(customer);
        reservation.setDate(new Date());

        reservationRepository.save(reservation);

        libraryController.lend(physicalMedia.getId(), customer.getId());

        assertEquals(0, reservationRepository.count());
        assertEquals(1, lendingRepository.findDistinctByPhysicalMediaEqualsAndCustomerEqualsOrderByStateAscLendDateAsc(physicalMedia, customer).size());
    }

    @Test(expected = IllegalStateException.class)
    public void reservedByOthersLendingTest() {
        List<Customer> customers = customerRepository.findAll();
        Customer customer = customers.get(0);
        Customer other = customers.get(1);
        PhysicalMedia physicalMedia = physicalMediaRepository.findAll().get(0);
        Media media = physicalMedia.getMedia();

        Reservation reservation = new Reservation();
        reservation.setMedia(media);
        reservation.setCustomer(customer);
        reservation.setDate(new Date());

        reservationRepository.save(reservation);

        // should fail
        libraryController.lend(physicalMedia.getId(), other.getId());
    }

    @Test(expected = IllegalStateException.class)
    public void alreadyLentLendingTest() {
        List<Customer> customers = customerRepository.findAll();
        Customer customer = customers.get(0);
        Customer other = customers.get(1);
        PhysicalMedia physicalMedia = physicalMediaRepository.findAll().get(0);

        // should work
        libraryController.lend(physicalMedia.getId(), customer.getId());

        // should fail
        libraryController.lend(physicalMedia.getId(), other.getId());
    }

    @Test
    public void returnLendingTest() {

        Customer customer = customerRepository.findAll().get(0);
        PhysicalMedia physicalMedia = physicalMediaRepository.findAll().get(0);

        LendingDTO lendingDTO = libraryController.lend(physicalMedia.getId(), customer.getId());

        libraryController.returnLending(lendingDTO.getId());

        Lending lending = lendingRepository.findOne(lendingDTO.getId());

        assertEquals(LendingState.RETURNED, lending.getState());
        assertEquals(Availability.AVAILABLE, lending.getPhysicalMedia().getAvailability());
    }

    @Test(expected = IllegalStateException.class)
    public void returnAlreadyReturnedLendingTest() {

        Customer customer = customerRepository.findAll().get(0);
        PhysicalMedia physicalMedia = physicalMediaRepository.findAll().get(0);

        LendingDTO lendingDTO = libraryController.lend(physicalMedia.getId(), customer.getId());

        // should work
        libraryController.returnLending(lendingDTO.getId());
        // should fail
        libraryController.returnLending(lendingDTO.getId());
    }

    @Test
    public void extendLendingTest() {
        Customer customer = customerRepository.findAll().get(0);
        PhysicalMedia physicalMedia = physicalMediaRepository.findAll().get(0);

        LendingDTO lendingDTO = libraryController.lend(physicalMedia.getId(), customer.getId());

        long lendingDate = lendingDTO.getLendDate().getTime();

        libraryController.extendLending(lendingDTO.getId());

        Lending lending = lendingRepository.findOne(lendingDTO.getId());

        assertTrue(lendingDate < lending.getLendDate().getTime());
    }

    @Test(expected = IllegalStateException.class)
    public void tooOftenExtendLendingTest() {
        Customer customer = customerRepository.findAll().get(0);
        PhysicalMedia physicalMedia = physicalMediaRepository.findAll().get(0);

        LendingDTO lendingDTO = libraryController.lend(physicalMedia.getId(), customer.getId());

        // should work
        for (int i = 0; i < daRulezRepository.findFirstBy().getMaxExtensions(); i++) {
            libraryController.extendLending(lendingDTO.getId());
        }

        // should fail
        libraryController.extendLending(lendingDTO.getId());
    }

    @Test(expected = IllegalStateException.class)
    public void extendAlreadyReturnedLendingTest() {
        Customer customer = customerRepository.findAll().get(0);
        PhysicalMedia physicalMedia = physicalMediaRepository.findAll().get(0);

        LendingDTO lendingDTO = libraryController.lend(physicalMedia.getId(), customer.getId());

        // should work
        libraryController.returnLending(lendingDTO.getId());

        // should fail
        libraryController.extendLending(lendingDTO.getId());
    }
}
