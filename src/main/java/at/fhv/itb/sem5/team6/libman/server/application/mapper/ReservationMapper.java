package at.fhv.itb.sem5.team6.libman.server.application.mapper;

import at.fhv.itb.sem5.team6.libman.server.model.Reservation;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.ReservationDTO;
import org.mapstruct.Mapper;

// make sure all properties have exactly the same name or mapping will not work
@Mapper(componentModel = "spring",
        uses = {MediaMapper.class, CustomerMapper.class}) //insert other mappers here if needed
public interface ReservationMapper extends TypedMapper<Reservation, ReservationDTO> {
}
