package at.fhv.itb.sem5.team6.libman.server.persistence;

import at.fhv.itb.sem5.team6.libman.server.model.Customer;
import at.fhv.itb.sem5.team6.libman.server.model.Media;
import at.fhv.itb.sem5.team6.libman.server.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
    List<Reservation> findDistinctByOrderByDateAsc();

    List<Reservation> findDistinctByMediaEquals(Media media);

    List<Reservation> findDistinctByMediaEqualsAndCustomerEquals(Media media, Customer customer);
}
