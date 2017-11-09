package at.fhv.itb.sem5.team6.libman.server.application;

import at.fhv.itb.sem5.team6.libman.server.application.mapper.*;
import at.fhv.itb.sem5.team6.libman.server.model.*;
import at.fhv.itb.sem5.team6.libman.server.persistence.*;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.*;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.Genre;
import at.fhv.itb.sem5.team6.libman.shared.enums.LendingState;
import at.fhv.itb.sem5.team6.libman.shared.enums.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class LibraryController {

    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private MediaMapper mediaMapper;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private PhysicalMediaRepository physicalMediaRepository;
    @Autowired
    private PhysicalMediaMapper physicalMediaMapper;

    @Autowired
    private LendingRepository lendingRepository;
    @Autowired
    private LendingMapper lendingMapper;

    // search
    public List<MediaDTO> findAllMedia(String text, Genre genre, MediaType type, Availability availability) {
        List<Media> result = new ArrayList<>();

        //fetch data
        if (text != null) {
            result = mediaRepository.findDistinctByTitleLikeOrAuthorLikeOrIsbnLikeOrPublisherLikeAllIgnoreCase(text, text, text, text);
        }

        Predicate<Media> filter = (
                media -> (type != null && media.getType() != type) ||
                        (genre != null && media.getGenre() != genre) ||
                        (availability != null && !physicalMediaRepository.findDistinctByMediaEqualsAndAvailabilityEquals(media, availability).isEmpty())
        );

        return mediaMapper.toDTOs(result.stream().filter(filter).collect(Collectors.toList()));
    }

    public List<PhysicalMediaDTO> findAllPhysicalMedia() {
        return physicalMediaMapper.toDTOs(physicalMediaRepository.findAll());
    }

    public List<PhysicalMediaDTO> getPhysicalMedia(MediaDTO media) {
        return physicalMediaMapper.toDTOs(physicalMediaRepository.findDistinctByMediaEquals(mediaMapper.toModel(media)));
    }

    // reservation
    public ReservationDTO reserveMedia(MediaDTO mediaDTO, CustomerDTO customerDTO) {
        // create new reservation object
        Reservation reservation = new Reservation();
        reservation.setMedia(mediaMapper.toModel(mediaDTO));
        reservation.setCustomer(customerMapper.toModel(customerDTO));
        reservation.setDate(new Date());

        // save reservation
        reservationRepository.save(reservation);

        // return reservation as dto
        return reservationMapper.toDTO(reservation);
    }

    public void cancelReservation(ReservationDTO reservationDTO) {
        Reservation reservation = reservationMapper.toModel(reservationDTO);
        reservationRepository.delete(reservation);
    }

    // lending
    public LendingDTO lendPhysicalMedia(PhysicalMediaDTO physicalMediaDTO, CustomerDTO customerDTO) {
        //Mapping of DTOs to models
        PhysicalMedia physicalMedia = physicalMediaMapper.toModel(physicalMediaDTO);
        Customer customer = customerMapper.toModel(customerDTO);

        // validate
        if (lendingRepository.findDistinctByPhysicalMediaEqualsAndStateEquals(physicalMedia, LendingState.LENT).size() > 0) {
            throw new IllegalArgumentException("Physical Media already lent");
        } else {
            //creation of lending
            Lending lending = new Lending();
            lending.setPhysicalMedia(physicalMedia);
            lending.setCustomer(customer);
            lending.setLendDate(new Date());
            lending.setState(LendingState.LENT);
            lending.setExtentions(0);

            // save new object
            lendingRepository.save(lending);

            //convert lending back to dto and return
            return lendingMapper.toDTO(lending);
        }
    }

    public void returnPhysicalMedia(PhysicalMediaDTO physicalMediaDTO) {
        //Mapping of DTOs to models
        PhysicalMedia physicalMedia = physicalMediaMapper.toModel(physicalMediaDTO);

        List<Lending> lendings = lendingRepository.findDistinctByPhysicalMediaEqualsAndStateEquals(physicalMedia, LendingState.LENT);

        if (lendings.size() > 1) {
            throw new IllegalArgumentException("Multiple Lendings found");
        } else if (lendings.isEmpty()) {
            throw new IllegalArgumentException("No Lending found");
        } else {
            lendings.get(0).setState(LendingState.RETURNED);
            lendingRepository.save(lendings.get(0));
        }
    }

    public void extendLending(LendingDTO lendingDTO) {
        Lending lending = lendingMapper.toModel(lendingDTO);
        if (lending.getExtentions() > 1) {
            throw new IllegalArgumentException("No more extensions possible");
        } else {
            lending.setExtentions(lending.getExtentions() + 1);
            lendingRepository.save(lending);
        }
    }
}
