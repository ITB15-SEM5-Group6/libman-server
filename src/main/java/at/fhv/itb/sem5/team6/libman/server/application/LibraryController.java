package at.fhv.itb.sem5.team6.libman.server.application;

import at.fhv.itb.sem5.team6.libman.server.model.Availability;
import at.fhv.itb.sem5.team6.libman.server.model.Media;
import at.fhv.itb.sem5.team6.libman.server.model.MediaType;
import at.fhv.itb.sem5.team6.libman.server.persistence.CustomerRepository;
import at.fhv.itb.sem5.team6.libman.server.persistence.MediaRepository;
import at.fhv.itb.sem5.team6.libman.server.persistence.PhysicalMediaRepository;
import at.fhv.itb.sem5.team6.libman.server.persistence.ReservationRepository;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.immutable.ImmutableMedia;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.immutable.ImmutablePhysicalMedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryController implements Convertible {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private PhysicalMediaRepository physicalMediaRepository;

    public List<ImmutableMedia> findAllMedia() {
        return castUp(mediaRepository.findAll());
    }

    public List<ImmutableMedia> findAllMedia(String text) {
        List<Media> result;
        try {
            MediaType type = MediaType.valueOf(text);
            result = mediaRepository.findDistinctByIdLikeOrTitleLikeOrTypeLikeAllIgnoreCase(text, text, type);
        } catch (IllegalArgumentException e) {
            result = mediaRepository.findDistinctByIdLikeOrTitleLikeAllIgnoreCase(text, text);
        }
        return castUp(result);
    }

    public List<ImmutableMedia> findAllMedia(MediaType type) {
        return castUp(mediaRepository.findDistinctByTypeEquals(type));
    }

    public List<ImmutableMedia> findAllMedia(Availability availability) {
        return castUp(mediaRepository.findAll().stream().filter(p -> physicalMediaRepository.findDistinctByAvailabilityEquals(availability).stream().anyMatch(o -> o.getMedia().equals(p))).collect(Collectors.toList()));
    }

    public List<ImmutableMedia> findAllMedia(String text, MediaType type, Availability availability) {
        List<ImmutableMedia> result = new ArrayList<>();
        List<ImmutableMedia> result1 = findAllMedia(text);
        List<ImmutableMedia> result2 = findAllMedia(type);
        List<ImmutableMedia> result3 = findAllMedia(availability);

        for(ImmutableMedia im : result1){
            if(result2.contains(im) && result3.contains(im)){
                result.add(im);
            }
        }

        return castUp(result);
    }

    public List<ImmutablePhysicalMedia> findAllPhysicalMedia() {
        return castUp(physicalMediaRepository.findAll());
    }

    public List<ImmutablePhysicalMedia> getPhysicalMedia(ImmutableMedia media) {
        return castUp(physicalMediaRepository.findDistinctByMediaEquals(castDown(media)));
    }
}