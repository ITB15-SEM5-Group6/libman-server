package at.fhv.itb.sem5.team6.libman.server;

import at.fhv.itb.sem5.team6.libman.server.application.mapper.*;
import at.fhv.itb.sem5.team6.libman.server.model.*;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.Genre;
import at.fhv.itb.sem5.team6.libman.shared.enums.LendingState;
import at.fhv.itb.sem5.team6.libman.shared.enums.MediaType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MappingTests {

    private Random r = new Random();

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private MediaMapper mediaMapper;
    @Autowired
    private PhysicalMediaMapper physicalMediaMapper;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private LendingMapper lendingMapper;

    @Test
    public void customerMappingTest() {
        mappingTest(customerMapper, Customer::new, this::randomizeCustomer);
    }

    @Test
    public void mediaMappingTest() {
        mappingTest(mediaMapper, Media::new, this::randomizeMedia);
    }

    @Test
    public void physicalMediaMappingTest() {
        mappingTest(physicalMediaMapper, PhysicalMedia::new, this::randomizePhysicalMedia);
    }

    @Test
    public void reservationMappingTest() {
        mappingTest(reservationMapper, Reservation::new, this::randomizeReservation);
    }

    @Test
    public void lendingMappingTest() {
        mappingTest(lendingMapper, Lending::new, this::randomizeLending);
    }

    private <Model, DTO> void mappingTest(TypedMapper<Model, DTO> mapper, Supplier<Model> initializer, Consumer<Model> randomizer) {
        singleMappingTest(mapper, initializer, randomizer);
        collectionMappingTest(mapper, initializer, randomizer);
    }

    private <Model, DTO> void singleMappingTest(TypedMapper<Model, DTO> mapper, Supplier<Model> initializer, Consumer<Model> randomizer) {
        Model model = initializer.get();
        DTO dto;

        assertEquals(model, mapper.toModel((dto = mapper.toDTO(model))));
        assertEquals(dto, mapper.toDTO(mapper.toModel(dto)));

        randomizer.accept(model);

        assertEquals(model, mapper.toModel((dto = mapper.toDTO(model))));
        assertEquals(dto, mapper.toDTO(mapper.toModel(dto)));
    }

    private <Model, DTO> void collectionMappingTest(TypedMapper<Model, DTO> mapper, Supplier<Model> initializer, Consumer<Model> randomizer) {
        int size = 2;

        List<Model> models = new ArrayList<>(size);
        List<DTO> dtos;

        assertEquals(models, mapper.toModels((dtos = mapper.toDTOs(models))));
        assertEquals(dtos, mapper.toDTOs(mapper.toModels(dtos)));

        for (int i = 1; i <= size; i++) {
            Model model = initializer.get();

            randomizer.accept(model);

            models.add(model);
        }

        assertEquals(models, mapper.toModels((dtos = mapper.toDTOs(models))));
        assertEquals(dtos, mapper.toDTOs(mapper.toModels(dtos)));
    }

    private Customer randomizeCustomer(Customer model) {
        model.setId(String.valueOf(r.nextInt()));
        model.setFirstName(String.valueOf(r.nextInt()));
        model.setLastName(String.valueOf(r.nextInt()));
        model.setAddress(String.valueOf(r.nextInt()));
        model.setEmail(String.valueOf(r.nextInt()));
        model.setPhoneNumber(String.valueOf(r.nextInt()));
        model.setBic(String.valueOf(r.nextInt()));
        model.setIban(String.valueOf(r.nextInt()));
        return model;
    }

    private Media randomizeMedia(Media model) {
        model.setId(String.valueOf(r.nextInt()));
        model.setType(MediaType.values()[r.nextInt(MediaType.values().length)]);
        model.setIsbn(String.valueOf(r.nextInt()));
        model.setAuthor(String.valueOf(r.nextInt()));
        model.setPublisher(String.valueOf(r.nextInt()));
        model.setReleaseDate(new Date());
        model.setTags(String.valueOf(r.nextInt()));
        model.setGenre(Genre.values()[r.nextInt(Genre.values().length)]);
        return model;
    }

    private PhysicalMedia randomizePhysicalMedia(PhysicalMedia model) {
        model.setId(String.valueOf(r.nextInt()));
        model.setIndex(String.valueOf(r.nextInt()));
        model.setAvailability(Availability.values()[r.nextInt(Availability.values().length)]);
        model.setMedia(randomizeMedia(new Media()));
        return model;
    }

    private Reservation randomizeReservation(Reservation model) {
        model.setId(String.valueOf(r.nextInt()));
        model.setMedia(randomizeMedia(new Media()));
        model.setCustomer(randomizeCustomer(new Customer()));
        model.setDate(new Date());
        return model;
    }

    private Lending randomizeLending(Lending model) {
        model.setId(String.valueOf(r.nextInt()));
        model.setPhysicalMedia(randomizePhysicalMedia(new PhysicalMedia()));
        model.setCustomer(randomizeCustomer(new Customer()));
        model.setLendDate(new Date());
        model.setExtensions(r.nextInt());
        model.setInfo(String.valueOf(r.nextInt()));
        model.setState(LendingState.values()[r.nextInt(LendingState.values().length)]);
        return model;
    }
}
