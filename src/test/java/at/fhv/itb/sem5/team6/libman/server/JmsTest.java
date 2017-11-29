package at.fhv.itb.sem5.team6.libman.server;

import at.fhv.itb.sem5.team6.libman.server.model.Customer;
import at.fhv.itb.sem5.team6.libman.server.model.PhysicalMedia;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.LendingDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
public class JmsTest extends EmbeddedMongoUnitTest {

    @Before
    public void setUp() {
        customer();
        media();
        physicalMedia(1);
    }

    @Test
    public void onReturnLendingWithReservations() {
        PhysicalMedia physicalMedia = physicalMediaRepository.findAll().get(0);
        List<Customer> customers = customerRepository.findAll().subList(0, 2);
        Customer first = customers.get(0);
        Customer second = customers.get(1);
        LendingDTO lending = libraryController.lend(physicalMedia.getId(), first.getId());

        libraryController.reserve(physicalMedia.getMedia().getId(), second.getId());

        libraryController.returnLending(lending.getId());

        String message = libraryController.getNextMessage();
        assertNotNull(message);
    }

}
