package at.fhv.itb.sem5.team6.libman.server.application;

import at.fhv.itb.sem5.team6.libman.server.application.mapper.CustomerMapper;
import at.fhv.itb.sem5.team6.libman.server.application.mapper.MediaMapper;
import at.fhv.itb.sem5.team6.libman.server.application.mapper.PhysicalMediaMapper;
import at.fhv.itb.sem5.team6.libman.server.application.mapper.ReservationMapper;
import at.fhv.itb.sem5.team6.libman.server.model.Media;
import at.fhv.itb.sem5.team6.libman.server.model.PhysicalMedia;
import at.fhv.itb.sem5.team6.libman.server.persistence.CustomerRepository;
import at.fhv.itb.sem5.team6.libman.server.persistence.MediaRepository;
import at.fhv.itb.sem5.team6.libman.server.persistence.PhysicalMediaRepository;
import at.fhv.itb.sem5.team6.libman.server.persistence.ReservationRepository;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.Genre;
import at.fhv.itb.sem5.team6.libman.shared.enums.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public List<MediaDTO> findAllMedia(String text, Genre genre, MediaType type, Availability availability) {
        List<Media> result = new ArrayList<>();

        //fetch data
        if(text != null)
        {
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
}
