package at.fhv.itb.sem5.team6.libman.server.persistence;

import at.fhv.itb.sem5.team6.libman.server.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    List<Customer> findDistinctByOrderByLastNameAscFirstNameAsc();

    List<Customer> findDistinctByFirstNameLikeOrLastNameLikeOrEmailLikeOrAddressLikeOrPhoneNumberLikeOrBicLikeOrIbanLikeAllIgnoreCaseOrderByLastNameAscFirstNameAsc(String firstName, String lastName, String email, String address, String phoneNumber, String bic, String iban);
}
