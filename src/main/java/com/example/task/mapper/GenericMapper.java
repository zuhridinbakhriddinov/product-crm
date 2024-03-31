package com.example.task.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface GenericMapper<E,M> {
    E toEntity(M model);

    M toModel(E entity);

    List<E> toEntityList(List<M> models);


    List<M> toModelList(List<E> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromModel(@MappingTarget E entity, M model);
}

