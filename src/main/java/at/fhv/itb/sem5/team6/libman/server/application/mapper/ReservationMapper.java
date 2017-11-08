package at.fhv.itb.sem5.team6.libman.server.application.mapper;

import at.fhv.itb.sem5.team6.libman.server.model.Reservation;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.ReservationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper extends TypedMapper<Reservation, ReservationDTO> {
}
