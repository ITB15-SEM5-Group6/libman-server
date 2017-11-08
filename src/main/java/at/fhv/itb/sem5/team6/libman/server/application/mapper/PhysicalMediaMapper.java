package at.fhv.itb.sem5.team6.libman.server.application.mapper;

import at.fhv.itb.sem5.team6.libman.server.model.PhysicalMedia;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhysicalMediaMapper extends TypedMapper<PhysicalMedia, PhysicalMediaDTO> {
}
