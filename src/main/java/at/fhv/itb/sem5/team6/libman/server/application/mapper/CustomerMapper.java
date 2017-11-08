package at.fhv.itb.sem5.team6.libman.server.application.mapper;

import at.fhv.itb.sem5.team6.libman.server.model.Customer;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends TypedMapper<Customer, CustomerDTO> {
}
