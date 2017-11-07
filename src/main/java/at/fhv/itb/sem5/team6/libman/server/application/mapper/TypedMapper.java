package at.fhv.itb.sem5.team6.libman.server.application.mapper;

import java.util.List;

public interface TypedMapper<Model, ModelDTO> {
    ModelDTO modelToDto(Model entity);

    List<ModelDTO> modelsToDtos(List<Model> entity);

    Model DtoToModel(ModelDTO entity);

    List<Model> DtosToModels(List<ModelDTO> entity);
}
