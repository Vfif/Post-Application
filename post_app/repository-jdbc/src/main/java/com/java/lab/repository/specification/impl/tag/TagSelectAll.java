package com.epam.lab.repository.specification.impl.tag;

import com.epam.lab.repository.specification.QuerySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

public class TagSelectAll implements QuerySpecification {
    private static final String SELECT_ALL_TAGS = "SELECT * FROM task2.tag";

    @Override
    public PreparedStatementCreator specify() {
        return connection -> connection.prepareStatement(SELECT_ALL_TAGS);
    }
}
