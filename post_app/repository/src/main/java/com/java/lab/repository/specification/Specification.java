package com.java.lab.repository.specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 * The interface Specification for Repository.
 *
 * @author Mariya Gurskaya
 * @since 1.0
 */
public interface Specification<T> {

    /**
     * Specify prepared statement creator.
     *
     * @return the prepared statement creator which contain
     * SQL request and SQL request parameters
     */
    CriteriaQuery<T> specify(EntityManager entityManager);
}

