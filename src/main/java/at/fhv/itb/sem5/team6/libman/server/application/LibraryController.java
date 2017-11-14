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

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class LibraryController {
    private final DaRulez daRulez;

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    private final PhysicalMediaRepository physicalMediaRepository;
    private final PhysicalMediaMapper physicalMediaMapper;

    private final LendingRepository lendingRepository;
    private final LendingMapper lendingMapper;

    @Autowired
    public LibraryController(DaRulezRepository daRulezRepository, MediaRepository mediaRepository, MediaMapper mediaMapper, CustomerRepository customerRepository, CustomerMapper customerMapper, ReservationRepository reservationRepository, ReservationMapper reservationMapper, PhysicalMediaRepository physicalMediaRepository, PhysicalMediaMapper physicalMediaMapper, LendingRepository lendingRepository, LendingMapper lendingMapper) {
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.physicalMediaRepository = physicalMediaRepository;
        this.physicalMediaMapper = physicalMediaMapper;
        this.lendingRepository = lendingRepository;
        this.lendingMapper = lendingMapper;

        daRulez = daRulezRepository.findFirstBy();
    }

    public List<MediaDTO> findMedias() {
        List<Media> medias = mediaRepository.findDistinctByOrderByTitleAscTypeAsc();
        return mediaMapper.toDTOs(medias);
    }

    public List<MediaDTO> findMedias(String text, Genre genre, MediaType mediaType, Availability availability) {
        List<Media> medias = mediaRepository.findDistinctByTitleLikeOrAuthorLikeOrIsbnLikeOrPublisherLikeAllIgnoreCaseOrderByTitleAscTypeAsc(text, text, text, text);

        Predicate<Media> filter = (
                media -> {
                    boolean mediaTypeFilter = (mediaType.equals(MediaType.ALL) || media.getType().equals(mediaType));
                    boolean genreFilter = (genre.equals(Genre.ALL) || media.getGenre().equals(genre));

                    int amountAvailablePhysicalMediaOfMedia = physicalMediaRepository.findDistinctByMediaEqualsAndAvailabilityEquals(media, Availability.AVAILABLE).size();
                    int amountReservationsOfMedia = reservationRepository.findDistinctByMediaEqualsOrderByDateAsc(media).size();

                    //TODO check for Availibility status before accessing db
                    boolean availableFilter = (availability.equals(Availability.ALL) ||
                            (availability.equals(Availability.AVAILABLE) && (amountAvailablePhysicalMediaOfMedia - amountReservationsOfMedia) > 0) ||
                            (availability.equals(Availability.NOT_AVAILABLE) && (amountAvailablePhysicalMediaOfMedia - amountReservationsOfMedia) <= 0));

                    return mediaTypeFilter && genreFilter && availableFilter;
                }
        );

        return mediaMapper.toDTOs(medias.stream().filter(filter).collect(Collectors.toList()));
    }

    public List<CustomerDTO> findCustomers() {
        List<Customer> customers = customerRepository.findDistinctByOrderByLastNameAscFirstNameAsc();
        return customerMapper.toDTOs(customers);
    }

    public List<CustomerDTO> findCustomers(String term) {
        List<Customer> customers = customerRepository.findDistinctByFirstNameLikeOrLastNameLikeOrEmailLikeOrAddressLikeOrPhoneNumberLikeOrBicLikeOrIbanLikeAllIgnoreCaseOrderByLastNameAscFirstNameAsc(term, term, term, term, term, term, term);
        return customerMapper.toDTOs(customers);
    }

    public List<PhysicalMediaDTO> findPhysicalMedias() {
        List<PhysicalMedia> physicalMedias = physicalMediaRepository.findDistinctByOrderByIndexAsc();
        return physicalMediaMapper.toDTOs(physicalMedias);
    }

    public List<PhysicalMediaDTO> findPhysicalMedias(MediaDTO mediaDTO) {
        Media media = mediaMapper.toModel(mediaDTO);
        List<PhysicalMedia> physicalMedias = physicalMediaRepository.findDistinctByMediaEqualsOrderByIndexAsc(media);
        return physicalMediaMapper.toDTOs(physicalMedias);
    }

    public List<ReservationDTO> findReservations() {
        List<Reservation> reservations = reservationRepository.findDistinctByOrderByDateAsc();
        return reservationMapper.toDTOs(reservations);
    }

    public List<ReservationDTO> findReservations(MediaDTO mediaDTO) {
        Media media = mediaMapper.toModel(mediaDTO);
        List<Reservation> reservations = reservationRepository.findDistinctByMediaEqualsOrderByDateAsc(media);
        return reservationMapper.toDTOs(reservations);
    }

    public List<ReservationDTO> findReservations(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toModel(customerDTO);
        List<Reservation> reservations = reservationRepository.findDistinctByCustomerEqualsOrderByDateAsc(customer);
        return reservationMapper.toDTOs(reservations);
    }

    public List<ReservationDTO> findReservations(MediaDTO mediaDTO, CustomerDTO customerDTO) {
        Media media = mediaMapper.toModel(mediaDTO);
        Customer customer = customerMapper.toModel(customerDTO);
        List<Reservation> reservations = reservationRepository.findDistinctByMediaEqualsAndCustomerEqualsOrderByDateAsc(media, customer);
        return reservationMapper.toDTOs(reservations);
    }

    public List<LendingDTO> findLendings() {
        List<Lending> lendings = lendingRepository.findDistinctByOrderByLendDateAsc();
        return lendingMapper.toDTOs(lendings);
    }

    public List<LendingDTO> findLendings(PhysicalMediaDTO physicalMediaDTO) {
        PhysicalMedia physicalMedia = physicalMediaMapper.toModel(physicalMediaDTO);
        List<Lending> lendings = lendingRepository.findDistinctByPhysicalMediaEqualsOrderByLendDateAsc(physicalMedia);
        return lendingMapper.toDTOs(lendings);
    }

    public List<LendingDTO> findLendings(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toModel(customerDTO);
        List<Lending> lendings = lendingRepository.findDistinctByCustomerEqualsOrderByLendDateAsc(customer);
        return lendingMapper.toDTOs(lendings);
    }

    public List<LendingDTO> findLendings(PhysicalMediaDTO physicalMediaDTO, CustomerDTO customerDTO) {
        PhysicalMedia physicalMedia = physicalMediaMapper.toModel(physicalMediaDTO);
        Customer customer = customerMapper.toModel(customerDTO);
        List<Lending> lendings = lendingRepository.findDistinctByPhysicalMediaEqualsAndCustomerEqualsOrderByLendDateAsc(physicalMedia, customer);
        return lendingMapper.toDTOs(lendings);
    }

    public ReservationDTO reserve(MediaDTO mediaDTO, CustomerDTO customerDTO) {
        //TODO: maybe not needed
        if (mediaDTO == null) {
            throw new IllegalArgumentException("media must not be null");
        }

        if (customerDTO == null) {
            throw new IllegalArgumentException("customer must not be null");
        }

        Media media = mediaMapper.toModel(mediaDTO);
        Customer customer = customerMapper.toModel(customerDTO);

        if (!reservationRepository.findDistinctByMediaEqualsAndCustomerEqualsOrderByDateAsc(media, customer).isEmpty()) {
            throw new IllegalStateException("already reserved");
        }

        if ((physicalMediaRepository.findDistinctByMediaEqualsAndAvailabilityEquals(media, Availability.AVAILABLE).size() - reservationRepository.findDistinctByMediaEqualsOrderByDateAsc(media).size()) > 0) {
            throw new IllegalStateException("no need to reserve");
        }

        // create new reservation object
        Reservation reservation = new Reservation();
        reservation.setMedia(media);
        reservation.setCustomer(customer);
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

    public LendingDTO lend(PhysicalMediaDTO physicalMediaDTO, CustomerDTO customerDTO) {
        // TODO maybe not needed
        if (physicalMediaDTO == null) {
            throw new IllegalArgumentException("physical media must not be null");
        }

        if (customerDTO == null) {
            throw new IllegalArgumentException("customer must not be null");
        }

        PhysicalMedia physicalMedia = physicalMediaMapper.toModel(physicalMediaDTO);
        Customer customer = customerMapper.toModel(customerDTO);

        if (physicalMedia.getAvailability() == Availability.NOT_AVAILABLE) {
            throw new IllegalStateException("phyiscal media is not available");
        }

        int amountAvailablePhysicalMediasOfMedia = physicalMediaRepository.findDistinctByMediaEqualsAndAvailabilityEquals(physicalMedia.getMedia(), physicalMedia.getAvailability()).size();
        int amountReservationsOfMedia = reservationRepository.findDistinctByMediaEqualsOrderByDateAsc(physicalMedia.getMedia()).size();

        if ((amountAvailablePhysicalMediasOfMedia - amountReservationsOfMedia) <= 0) {
            throw new IllegalStateException("physical media is already reserved");
        }

        physicalMedia.setAvailability(Availability.NOT_AVAILABLE);

        Lending lending = new Lending();
        lending.setPhysicalMedia(physicalMedia);
        lending.setCustomer(customer);
        lending.setLendDate(new Date());
        lending.setState(LendingState.LENT);
        lending.setExtensions(0);

        physicalMediaRepository.save(physicalMedia);
        lendingRepository.save(lending);

        return lendingMapper.toDTO(lending);
    }


    public void returnLending(LendingDTO lendingDTO) {
        // TODO maybe not needed
        if (lendingDTO == null) {
            throw new IllegalArgumentException("lending must not be null");
        }

        Lending lending = lendingMapper.toModel(lendingDTO);

        if (lending.getState() == LendingState.RETURNED) {
            throw new IllegalStateException("lending is already returned");
        }

        PhysicalMedia physicalMedia = lending.getPhysicalMedia();
        physicalMedia.setAvailability(Availability.AVAILABLE);

        lending.setState(LendingState.RETURNED);

        physicalMediaRepository.save(physicalMedia);
        lendingRepository.save(lending);
    }

    public LendingDTO extendLending(LendingDTO lendingDTO) {
        // TODO maybe not needed
        if (lendingDTO == null) {
            throw new IllegalArgumentException("lending must not be null");
        }

        Lending lending = lendingMapper.toModel(lendingDTO);

        if (lending.getState() == LendingState.RETURNED) {
            throw new IllegalStateException("lending is already returned");
        }

        if (lending.getExtensions() >= daRulez.getMaxExtensions()) {
            throw new IllegalStateException("lending may not be extended more than " + daRulez.getMaxExtensions());
        }

        lending.setExtensions(lending.getExtensions() + 1);

        lendingRepository.save(lending);

        return lendingMapper.toDTO(lending);
    }
}
