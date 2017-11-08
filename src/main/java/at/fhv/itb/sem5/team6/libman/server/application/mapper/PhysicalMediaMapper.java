package at.fhv.itb.sem5.team6.libman.server.application.mapper;

import at.fhv.itb.sem5.team6.libman.server.model.PhysicalMedia;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhysicalMediaMapper extends TypedMapper<PhysicalMedia, PhysicalMediaDTO> {
}
