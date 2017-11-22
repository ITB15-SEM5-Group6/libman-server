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
    private final DaRulezRepository daRulezRepository;

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
        this.daRulezRepository = daRulezRepository;
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
    }

    public List<MediaDTO> findMedias() {
        List<Media> medias = mediaRepository.findDistinctByOrderByTypeAscTitleAsc();
        return mediaMapper.toDTOs(medias);
    }

    public List<MediaDTO> findMedias(String term, Genre genre, MediaType mediaType, Availability availability) {
        List<Media> medias = mediaRepository.findDistinctByTitleLikeOrAuthorLikeOrIsbnLikeOrPublisherLikeAllIgnoreCaseOrderByTypeAscTitleAsc(term, term, term, term);

        Predicate<Media> filter = (
                media -> {
                    boolean mediaTypeFilter = (mediaType.equals(MediaType.ALL) || media.getType().equals(mediaType));
                    boolean genreFilter = (genre.equals(Genre.ALL) || media.getGenre().equals(genre));

                    int amountAvailablePhysicalMediaOfMedia = physicalMediaRepository.findDistinctByMediaEqualsAndAvailabilityEqualsOrderByIndexAsc(media, Availability.AVAILABLE).size();
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

    public List<PhysicalMediaDTO> findPhysicalMediasByMedia(String physicalMediaId) {
        Media media = mediaRepository.findOne(physicalMediaId);
        List<PhysicalMedia> physicalMedias = physicalMediaRepository.findDistinctByMediaEqualsOrderByIndexAsc(media);
        return physicalMediaMapper.toDTOs(physicalMedias);
    }

    public List<ReservationDTO> findReservations() {
        List<Reservation> reservations = reservationRepository.findDistinctByOrderByDateAsc();
        return reservationMapper.toDTOs(reservations);
    }

    public List<ReservationDTO> findReservationsByMedia(String mediaId) {
        Media media = mediaRepository.findOne(mediaId);
        List<Reservation> reservations = reservationRepository.findDistinctByMediaEqualsOrderByDateAsc(media);
        return reservationMapper.toDTOs(reservations);
    }

    public List<ReservationDTO> findReservationsByCustomer(String customerId) {
        Customer customer = customerRepository.findOne(customerId);
        List<Reservation> reservations = reservationRepository.findDistinctByCustomerEqualsOrderByDateAsc(customer);
        return reservationMapper.toDTOs(reservations);
    }

    public List<ReservationDTO> findReservationsByMediaAndCustomer(String mediaId, String customerId) {
        Media media = mediaRepository.findOne(mediaId);
        Customer customer = customerRepository.findOne(customerId);
        List<Reservation> reservations = reservationRepository.findDistinctByMediaEqualsAndCustomerEqualsOrderByDateAsc(media, customer);
        return reservationMapper.toDTOs(reservations);
    }

    public List<LendingDTO> findLendings() {
        List<Lending> lendings = lendingRepository.findDistinctByOrderByLendDateAsc();
        return lendingMapper.toDTOs(lendings);
    }

    public List<LendingDTO> findLendings(LendingState lendingState) {
        List<Lending> lendings = lendingRepository.findDistinctByStateEqualsOrderByStateAscLendDateAsc(lendingState);
        return lendingMapper.toDTOs(lendings);
    }

    public List<LendingDTO> findLendingsByPhysicalMedia(String physicalMediaId) {
        PhysicalMedia physicalMedia = physicalMediaRepository.findOne(physicalMediaId);
        List<Lending> lendings = lendingRepository.findDistinctByPhysicalMediaEqualsOrderByStateAscLendDateAsc(physicalMedia);
        return lendingMapper.toDTOs(lendings);
    }

    public List<LendingDTO> findLendingsByPhysicalMedia(String physicalMediaId, LendingState lendingState) {
        PhysicalMedia physicalMedia = physicalMediaRepository.findOne(physicalMediaId);
        List<Lending> lendings = lendingRepository.findDistinctByPhysicalMediaEqualsAndStateEqualsOrderByStateAscLendDateAsc(physicalMedia, lendingState);
        return lendingMapper.toDTOs(lendings);
    }

    public List<LendingDTO> findLendingsByCustomer(String customerId) {
        Customer customer = customerRepository.findOne(customerId);
        List<Lending> lendings = lendingRepository.findDistinctByCustomerEqualsOrderByStateAscLendDateAsc(customer);
        return lendingMapper.toDTOs(lendings);
    }

    public List<LendingDTO> findLendingsByCustomer(String customerId, LendingState lendingState) {
        Customer customer = customerRepository.findOne(customerId);
        List<Lending> lendings = lendingRepository.findDistinctByCustomerEqualsAndStateEqualsOrderByStateAscLendDateAsc(customer, lendingState);
        return lendingMapper.toDTOs(lendings);
    }

    public List<LendingDTO> findLendingsByPhysicalMediaAndCustomer(String physicalMediaId, String customerId) {
        PhysicalMedia physicalMedia = physicalMediaRepository.findOne(physicalMediaId);
        Customer customer = customerRepository.findOne(customerId);
        List<Lending> lendings = lendingRepository.findDistinctByPhysicalMediaEqualsAndCustomerEqualsOrderByStateAscLendDateAsc(physicalMedia, customer);
        return lendingMapper.toDTOs(lendings);
    }

    public List<LendingDTO> findLendingsByPhysicalMediaAndCustomer(String physicalMediaId, String customerId, LendingState lendingState) {
        PhysicalMedia physicalMedia = physicalMediaRepository.findOne(physicalMediaId);
        Customer customer = customerRepository.findOne(customerId);
        List<Lending> lendings = lendingRepository.findDistinctByPhysicalMediaEqualsAndCustomerEqualsAndStateEqualsOrderByStateAscLendDateAsc(physicalMedia, customer, lendingState);
        return lendingMapper.toDTOs(lendings);
    }

    public ReservationDTO reserve(String mediaId, String customerId) {
        Media media = mediaRepository.findOne(mediaId);
        Customer customer = customerRepository.findOne(customerId);

        if (!reservationRepository.findDistinctByMediaEqualsAndCustomerEqualsOrderByDateAsc(media, customer).isEmpty()) {
            throw new IllegalStateException("already reserved");
        }

        if ((physicalMediaRepository.findDistinctByMediaEqualsAndAvailabilityEqualsOrderByIndexAsc(media, Availability.AVAILABLE).size() - reservationRepository.findDistinctByMediaEqualsOrderByDateAsc(media).size()) > 0) {
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

    public void cancelReservation(String reservationId) {
        reservationRepository.delete(reservationId);
    }

    public LendingDTO lend(String physicalMediaId, String customerId) {
        PhysicalMedia physicalMedia = physicalMediaRepository.findOne(physicalMediaId);
        Customer customer = customerRepository.findOne(customerId);

        if (physicalMedia.getAvailability() == Availability.NOT_AVAILABLE) {
            throw new IllegalStateException("phyiscal media is not available");
        }

        List<Reservation> reservationsOfMedia = reservationRepository.findDistinctByMediaEqualsOrderByDateAsc(physicalMedia.getMedia());
        int i = -1;
        for (Reservation reservation : reservationsOfMedia) {
            if (customer.equals(reservation.getCustomer())) {
                i = reservationsOfMedia.indexOf(reservation);
                break;
            }
        }

        int amountAvailablePhysicalMediasOfMedia = physicalMediaRepository.findDistinctByMediaEqualsAndAvailabilityEqualsOrderByIndexAsc(physicalMedia.getMedia(), physicalMedia.getAvailability()).size();

        int substractor;
        if (i < 0) {
            substractor = reservationRepository.findDistinctByMediaEqualsOrderByDateAsc(physicalMedia.getMedia()).size();
        } else {
            substractor = i;
        }

        if ((amountAvailablePhysicalMediasOfMedia - substractor) <= 0) {
            throw new IllegalStateException("physical media is already reserved");
        }

        physicalMedia.setAvailability(Availability.NOT_AVAILABLE);

        Lending lending = new Lending();
        lending.setPhysicalMedia(physicalMedia);
        lending.setCustomer(customer);
        lending.setLendDate(new Date());
        lending.setState(LendingState.LENT);
        lending.setExtensions(0);

        if (i >= 0) {
            Reservation reservationOfCustomer = reservationRepository.findDistinctByMediaEqualsAndCustomerEqualsOrderByDateAsc(physicalMedia.getMedia(), customer).get(0);
            reservationRepository.delete(reservationOfCustomer);
        }

        physicalMediaRepository.save(physicalMedia);
        lendingRepository.save(lending);

        return lendingMapper.toDTO(lending);
    }


    public void returnLending(String lendingId) {
        Lending lending = lendingRepository.findOne(lendingId);

        if (lending.getState() == LendingState.RETURNED) {
            throw new IllegalStateException("lending is already returned");
        }

        PhysicalMedia physicalMedia = lending.getPhysicalMedia();
        physicalMedia.setAvailability(Availability.AVAILABLE);

        lending.setState(LendingState.RETURNED);

        physicalMediaRepository.save(physicalMedia);
        lendingRepository.save(lending);
    }

    public LendingDTO extendLending(String lendingId) {
        Lending lending = lendingRepository.findOne(lendingId);

        if (lending.getState() == LendingState.RETURNED) {
            throw new IllegalStateException("lending is already returned");
        }

        DaRulez daRulez = daRulezRepository.findFirstBy();
        if (lending.getExtensions() >= daRulez.getMaxExtensions()) {
            throw new IllegalStateException("lending may not be extended more than " + daRulez.getMaxExtensions() + " times");
        }

        lending.setLendDate(new Date());

        lendingRepository.save(lending);

        return lendingMapper.toDTO(lending);
    }
}
