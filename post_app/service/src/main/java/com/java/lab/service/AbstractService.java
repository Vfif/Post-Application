package com.java.lab.service;

import com.java.lab.dto.Dto;
import com.java.lab.model.BaseEntity;

import java.util.List;

/**
 * The interface Abstract service.
 * Implements program logic.
 *
 * @param <T> the type parameter extends Dto
 * @param <S> the type parameter extends Entity
 */
public interface AbstractService<T extends Dto, S extends BaseEntity> {
    /**
     * Gets all entities from database.
     *
     * @return the all entities
     */
    List<T> getAll();

    /**
     * Gets entity by id.
     *
     * @param id the entity id which need to find
     * @return the entity with id
     */
    T getById(long id);

    /**
     * Save new entity in database.
     *
     * @param entity the entity can be without some fields
     * @return the entity which was created in database
     */
    T save(T entity);

    /**
     * Update entity in database.
     * Rewrite entity completely with given id.
     *
     * @param entity the entity
     * @param id     the id
     */
    T update(T entity, long id);

    /**
     * Part entity update.
     * Can rewrite some fields of entity with given id.
     *
     * @param entity the entity contain not-null fields that need to rewrite.
     * @param id     the entity id
     */
    T partUpdate(T entity, long id);

    /**
     * Delete entity with given id in database.
     *
     * @param id the entity id
     */
    void delete(long id);

    /**
     * Convert entity to dto.
     *
     * @param entity the entity
     * @return the dto
     */
    T convertToDto(S entity);

    /**
     * Convert dto to entity.
     *
     * @param dto the dto
     * @return the entity
     */
    S convertToEntity(T dto);
}
