package com.epam.lab.repository.specification;

import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 * The interface Specification for Repository.
 *
 * @author Mariya Gurskaya
 * @see UpdateSpecification
 * @see QuerySpecification
 * @since 1.0
 */
public interface Specification {

    /**
     * Specify prepared statement creator.
     *
     * @return the prepared statement creator which contain
     * SQL request and SQL request parameters
     */
    PreparedStatementCreator specify();
}

