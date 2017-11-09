package at.fhv.itb.sem5.team6.libman.server.application.mapper;

import at.fhv.itb.sem5.team6.libman.server.model.Lending;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.LendingDTO;
import org.mapstruct.Mapper;

// make sure all properties have exactly the same name or mapping will not work
@Mapper(componentModel = "spring",
        uses = {CustomerMapper.class, PhysicalMediaMapper.class}) //insert other mappers here if needed
public interface LendingMapper extends TypedMapper<Lending, LendingDTO> {
}
