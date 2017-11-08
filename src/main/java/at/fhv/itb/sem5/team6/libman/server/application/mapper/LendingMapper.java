package at.fhv.itb.sem5.team6.libman.server.application.mapper;

import at.fhv.itb.sem5.team6.libman.server.model.Lending;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.LendingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LendingMapper extends TypedMapper<Lending, LendingDTO> {
}
