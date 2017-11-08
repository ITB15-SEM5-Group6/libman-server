package at.fhv.itb.sem5.team6.libman.server.application.mapper;

import java.util.List;

public interface TypedMapper<Model, ModelDTO> {
    ModelDTO toDTO(Model entity);

    List<ModelDTO> toDTOs(List<Model> entity);

    Model toModel(ModelDTO entity);

    List<Model> toModels(List<ModelDTO> entity);
}
