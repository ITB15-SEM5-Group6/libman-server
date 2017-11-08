package at.fhv.itb.sem5.team6.libman.server.application.mapper;

import at.fhv.itb.sem5.team6.libman.server.model.Lending;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.LendingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LendingMapper extends TypedMapper<Lending, LendingDTO> {
}
