package at.fhv.itb.sem5.team6.libman.server;

import at.fhv.itb.sem5.team6.libman.server.application.LibraryController;
import at.fhv.itb.sem5.team6.libman.server.application.mapper.CustomerMapper;
import at.fhv.itb.sem5.team6.libman.server.application.mapper.MediaMapper;
import at.fhv.itb.sem5.team6.libman.server.application.mapper.ReservationMapper;
import at.fhv.itb.sem5.team6.libman.server.model.Customer;
import at.fhv.itb.sem5.team6.libman.server.model.Media;
import at.fhv.itb.sem5.team6.libman.server.persistence.CustomerRepository;
import at.fhv.itb.sem5.team6.libman.server.persistence.MediaRepository;
import at.fhv.itb.sem5.team6.libman.server.persistence.ReservationRepository;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.ReservationDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReservationTests extends EmbeddedMongoUnitTest {

    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CustomerMapper customerMapper;

    @Autowired
    protected MediaRepository mediaRepository;
    @Autowired
    protected MediaMapper mediaMapper;

    @Autowired
    protected ReservationRepository reservationRepository;
    @Autowired
    protected ReservationMapper reservationMapper;

    @Autowired
    protected LibraryController libraryController;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        reservationRepository.deleteAll();
        mediaRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    public void simpleTest() {
        Customer c = new Customer();
        c.setFirstName("Kurt");
        c.setLastName("Hobel");
        customerRepository.save(c);

        Media m = new Media();
        m.setTitle("Harry Potter und der Stein der Weisen");
        mediaRepository.save(m);

        CustomerDTO customerDTO = customerMapper.toDTO(c);
        MediaDTO mediaDTO = mediaMapper.toDTO(m);

        ReservationDTO reservationDTO = libraryController.reserveMedia(mediaDTO, customerDTO);
        libraryController.reserveMedia(mediaDTO, customerDTO);

        assertNotNull(reservationDTO);
        assertEquals(1, reservationRepository.count());
    }
}
