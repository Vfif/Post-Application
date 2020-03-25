package com.epam.lab.repository.specification.impl.post;

import com.epam.lab.repository.specification.QuerySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

public class PostSelectAll implements QuerySpecification {
    private static final String SELECT_ALL_NEWS = "SELECT * FROM task2.post";

    @Override
    public PreparedStatementCreator specify() {
        return connection -> connection.prepareStatement(SELECT_ALL_NEWS);
    }
}
