package at.fhv.itb.sem5.team6.libman.server;

import at.fhv.itb.sem5.team6.libman.server.application.LibraryController;
import at.fhv.itb.sem5.team6.libman.server.application.mapper.CustomerMapper;
import at.fhv.itb.sem5.team6.libman.server.application.mapper.LendingMapper;
import at.fhv.itb.sem5.team6.libman.server.application.mapper.PhysicalMediaMapper;
import at.fhv.itb.sem5.team6.libman.server.model.Lending;
import at.fhv.itb.sem5.team6.libman.server.persistence.CustomerRepository;
import at.fhv.itb.sem5.team6.libman.server.persistence.LendingRepository;
import at.fhv.itb.sem5.team6.libman.server.persistence.PhysicalMediaRepository;
import at.fhv.itb.sem5.team6.libman.server.persistence.ReservationRepository;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.LendingDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.enums.LendingState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LendingTests {

    @Autowired
    private LibraryController libraryController;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private PhysicalMediaRepository physicalMediaRepository;
    @Autowired
    private PhysicalMediaMapper physicalMediaMapper;

    @Autowired
    private LendingRepository lendingRepository;
    @Autowired
    private LendingMapper lendingMapper;

    @Test
    public void testLending() {
        CustomerDTO customerDTO = customerMapper.toDTO(customerRepository.findAll().get(0));
        PhysicalMediaDTO physicalMediaDTO = physicalMediaMapper.toDTO(physicalMediaRepository.findAll().get(0));

        List<Lending> lendingList = lendingRepository.findDistinctByPhysicalMediaEqualsAndStateEquals(physicalMediaMapper.toModel(physicalMediaDTO), LendingState.LENT);

        lendingList.forEach(lending -> lendingRepository.delete(lending));

        LendingDTO lendingDTO = libraryController.lendPhysicalMedia(physicalMediaDTO, customerDTO);

        assert lendingDTO.getCustomer().equals(customerDTO);
        //assert lendingDTO.getLendDate().equals(new Date());
        assert lendingDTO.getExtensions().equals(0);
        assert lendingDTO.getPhysicalMedia().equals(physicalMediaDTO);
        assert lendingDTO.getState().equals(LendingState.LENT);

        try {
            lendingDTO = libraryController.lendPhysicalMedia(physicalMediaDTO, customerDTO);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Physical Media already lent");
        }

        lendingRepository.delete(lendingMapper.toModel(lendingDTO));
    }
}
