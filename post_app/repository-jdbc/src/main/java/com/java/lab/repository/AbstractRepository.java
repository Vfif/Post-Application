package com.epam.lab.repository;

import com.epam.lab.model.Entity;
import com.epam.lab.repository.impl.AuthorRepository;
import com.epam.lab.repository.impl.PostRepository;
import com.epam.lab.repository.impl.TagRepository;
import com.epam.lab.repository.specification.QuerySpecification;
import com.epam.lab.repository.specification.Specification;
import com.epam.lab.repository.specification.UpdateSpecification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Abstract repository.
 * Designed for connection with database.
 * Used to get or change information in database.
 *
 * @param <T> the type parameter of Repository extends Entity
 * @author Mariya Gurskaya
 * @see Specification
 * @see UpdateSpecification
 * @see QuerySpecification
 * @see PostRepository
 * @see AuthorRepository
 * @see TagRepository
 * @see UserRepository
 * @since 1.0
 */

@Repository
public interface AbstractRepository<T extends Entity> {

    /**
     * Save entity in database.
     *
     * @param entity the entity which will be added in database
     * @return entity that has been saved
     */
    T save(T entity);

    /**
     * Remove entity with given id from database.
     *
     * @param id the entity id
     */
    void remove(long id);

    /**
     * Query list of entities according to specification.
     *
     * @param specification the specification for SELECT SQL request
     * @return the list of entities according to sql request in specification
     */
    List<T> query(QuerySpecification specification);

    /**
     * Update data in database.
     *
     * @param specification the specification for UPDATE SQL request
     */
    void update(UpdateSpecification specification);
}




