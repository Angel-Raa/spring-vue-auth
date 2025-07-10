package io.github.angel.raa.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface Mapper<D, E> {
    D toDto(E entity);

    E toEntity(D dto);

    default List<D> toDtoList(List<E> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    default List<E> toEntityList(List<D> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }

    default Set<D> toDtoSet(Set<E> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toSet());

    }

    default Set<E> toEntitySet(Set<D> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toSet());
    }

}
