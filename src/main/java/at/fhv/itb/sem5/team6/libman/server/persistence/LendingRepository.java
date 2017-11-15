package at.fhv.itb.sem5.team6.libman.server.persistence;

import at.fhv.itb.sem5.team6.libman.server.model.Customer;
import at.fhv.itb.sem5.team6.libman.server.model.Lending;
import at.fhv.itb.sem5.team6.libman.server.model.PhysicalMedia;
import at.fhv.itb.sem5.team6.libman.shared.enums.LendingState;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LendingRepository extends MongoRepository<Lending,String>{
    List<Lending> findDistinctByOrderByLendDateAsc();

    List<Lending> findDistinctByPhysicalMediaEqualsOrderByLendDateAsc(PhysicalMedia physicalMedia);

    List<Lending> findDistinctByCustomerEqualsOrderByLendDateAsc(Customer customer);

    List<Lending> findDistinctByPhysicalMediaEqualsAndCustomerEqualsOrderByLendDateAsc(PhysicalMedia physicalMedia, Customer customer);

    List<Lending> findDistinctByStateEqualsOrderByLendDateAsc(LendingState lendingState);

    List<Lending> findDistinctByPhysicalMediaEqualsAndStateEqualsOrderByLendDateAsc(PhysicalMedia physicalMedia, LendingState lendingState);

    List<Lending> findDistinctByCustomerEqualsAndStateEqualsOrderByLendDateAsc(Customer customer, LendingState lendingState);

    List<Lending> findDistinctByPhysicalMediaEqualsAndCustomerEqualsAndStateEqualsOrderByLendDateAsc(PhysicalMedia physicalMedia, Customer customer, LendingState lendingState);
}
