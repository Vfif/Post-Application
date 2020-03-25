package com.epam.lab.repository.specification.impl.author;

import com.epam.lab.repository.specification.QuerySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

public class AuthorSelectAll implements QuerySpecification {
    private static final String SELECT_ALL_AUTHORS = "SELECT * FROM task2.author";

    @Override
    public PreparedStatementCreator specify() {
        return connection -> connection.prepareStatement(SELECT_ALL_AUTHORS);
    }
}
