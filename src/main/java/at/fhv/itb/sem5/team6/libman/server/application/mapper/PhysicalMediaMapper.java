package at.fhv.itb.sem5.team6.libman.server.application.mapper;

import at.fhv.itb.sem5.team6.libman.server.model.PhysicalMedia;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import org.mapstruct.Mapper;

// make sure all properties have exactly the same name or mapping will not work
@Mapper(componentModel = "spring",
        uses = MediaMapper.class) //insert other mappers here if needed
public interface PhysicalMediaMapper extends TypedMapper<PhysicalMedia, PhysicalMediaDTO> {
}
