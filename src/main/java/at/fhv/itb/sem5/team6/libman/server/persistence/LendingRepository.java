package at.fhv.itb.sem5.team6.libman.server.persistence;

import at.fhv.itb.sem5.team6.libman.server.model.Customer;
import at.fhv.itb.sem5.team6.libman.server.model.Lending;
import at.fhv.itb.sem5.team6.libman.server.model.PhysicalMedia;
import at.fhv.itb.sem5.team6.libman.shared.enums.LendingState;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LendingRepository extends MongoRepository<Lending,String>{
    List<Lending> findDistinctByOrderByLendDateAsc();

    List<Lending> findDistinctByPhysicalMediaEqualsOrderByStateAscLendDateAsc(PhysicalMedia physicalMedia);

    List<Lending> findDistinctByCustomerEqualsOrderByStateAscLendDateAsc(Customer customer);

    List<Lending> findDistinctByPhysicalMediaEqualsAndCustomerEqualsOrderByStateAscLendDateAsc(PhysicalMedia physicalMedia, Customer customer);

    List<Lending> findDistinctByStateEqualsOrderByStateAscLendDateAsc(LendingState lendingState);

    List<Lending> findDistinctByPhysicalMediaEqualsAndStateEqualsOrderByStateAscLendDateAsc(PhysicalMedia physicalMedia, LendingState lendingState);

    List<Lending> findDistinctByCustomerEqualsAndStateEqualsOrderByStateAscLendDateAsc(Customer customer, LendingState lendingState);

    List<Lending> findDistinctByPhysicalMediaEqualsAndCustomerEqualsAndStateEqualsOrderByStateAscLendDateAsc(PhysicalMedia physicalMedia, Customer customer, LendingState lendingState);
}
