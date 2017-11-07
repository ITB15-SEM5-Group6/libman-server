package at.fhv.itb.sem5.team6.libman.server.application;

import at.fhv.itb.sem5.team6.libman.server.model.Media;
import at.fhv.itb.sem5.team6.libman.server.persistence.CustomerRepository;
import at.fhv.itb.sem5.team6.libman.server.persistence.MediaRepository;
import at.fhv.itb.sem5.team6.libman.server.persistence.PhysicalMediaRepository;
import at.fhv.itb.sem5.team6.libman.server.persistence.ReservationRepository;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private PhysicalMediaRepository physicalMediaRepository;

    public List<MediaDTO> findAllMedia() {
        return castUp(mediaRepository.findAll());
    }

    public List<MediaDTO> findAllMedia(String text) {
        List<Media> result;
        try {
            MediaType type = MediaType.valueOf(text);
            result = mediaRepository.findDistinctByIdLikeOrTitleLikeOrTypeLikeAllIgnoreCase(text, text, type);
        } catch (IllegalArgumentException e) {
            result = mediaRepository.findDistinctByIdLikeOrTitleLikeAllIgnoreCase(text, text);
        }
        return castUp(result);
    }

    public List<MediaDTO> findAllMedia(MediaType type) {
        return castUp(mediaRepository.findDistinctByTypeEquals(type));
    }

    public List<MediaDTO> findAllMedia(Availability availability) {
        return castUp(mediaRepository.findAll().stream().filter(p -> physicalMediaRepository.findDistinctByAvailabilityEquals(availability).stream().anyMatch(o -> o.getMedia().equals(p))).collect(Collectors.toList()));
    }

    public List<MediaDTO> findAllMedia(String text, MediaType type, Availability availability) {
        List<MediaDTO> result = new ArrayList<>();
        List<MediaDTO> result1 = findAllMedia(text);
        List<MediaDTO> result2 = findAllMedia(type);
        List<MediaDTO> result3 = findAllMedia(availability);

        for (MediaDTO im : result1) {
            if(result2.contains(im) && result3.contains(im)){
                result.add(im);
            }
        }

        return castUp(result);
    }

    public List<PhysicalMediaDTO> findAllPhysicalMedia() {
        return castUp(physicalMediaRepository.findAll());
    }

    public List<PhysicalMediaDTO> getPhysicalMedia(MediaDTO media) {
        return castUp(physicalMediaRepository.findDistinctByMediaEquals(castDown(media)));
    }
}
