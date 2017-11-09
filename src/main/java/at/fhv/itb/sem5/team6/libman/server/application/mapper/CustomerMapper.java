package at.fhv.itb.sem5.team6.libman.server.application.mapper;

import at.fhv.itb.sem5.team6.libman.server.model.Customer;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.CustomerDTO;
import org.mapstruct.Mapper;

// make sure all properties have exactly the same name or mapping will not work
@Mapper(componentModel = "spring",
        uses = {}) //insert other mappers here if needed
public interface CustomerMapper extends TypedMapper<Customer, CustomerDTO> {
}
