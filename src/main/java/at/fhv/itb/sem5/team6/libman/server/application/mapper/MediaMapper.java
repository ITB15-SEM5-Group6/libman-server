package at.fhv.itb.sem5.team6.libman.server.application.mapper;

import at.fhv.itb.sem5.team6.libman.server.model.Media;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MediaMapper extends TypedMapper<Media, MediaDTO> {
}
