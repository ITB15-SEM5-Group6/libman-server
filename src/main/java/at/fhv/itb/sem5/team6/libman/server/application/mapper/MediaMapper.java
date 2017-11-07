package at.fhv.itb.sem5.team6.libman.server.application.mapper;

import at.fhv.itb.sem5.team6.libman.server.model.Media;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MediaMapper {
    MediaDTO modelToDto(Media entity);

    List<MediaDTO> modelsToDtos(List<Media> entity);

    Media DtoToModel(MediaDTO entity);

    List<Media> DtosToModels(List<MediaDTO> entity);
}
