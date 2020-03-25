package com.java.lab.repository;

import com.java.lab.model.BaseEntity;
import com.java.lab.repository.specification.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * The interface Abstract repository.
 * Designed for connection with database.
 * Used to get or change information in database.
 *
 * @param <T> the type parameter of Repository extends Entity
 * @author Mariya Gurskaya
 * @see Specification
 * @see PostRepository
 * @see AuthorRepository
 * @see TagRepository
 * @since 1.0
 */
@Repository
public abstract class AbstractRepository<T extends BaseEntity> {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Gets entity manager.
     *
     * @return the entity manager
     */
    EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Save entity in database.
     *
     * @param entity the entity which will be added in database
     */
    public void save(T entity) {
        entityManager.persist(entity);
    }

    /**
     * Remove entity with given id from database.
     *
     * @param id the id
     */
    public void remove(long id) {
        entityManager.remove(findById(id));
    }

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     */
    protected abstract T findById(long id);

    /**
     * Query list of entities according to specification.
     *
     * @param specification the specification for SELECT SQL request
     * @return the list of entities according to sql request in specification
     */
    public List<T> query(Specification<T> specification) {
        return entityManager.createQuery(specification.specify(entityManager)).getResultList();
    }

    /**
     * Update t.
     *
     * @param entity the entity
     * @return the t
     */
    public T update(T entity) {
        return entityManager.merge(entity);
    }
}




