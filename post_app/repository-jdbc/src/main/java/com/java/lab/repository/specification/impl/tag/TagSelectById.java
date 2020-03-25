package com.epam.lab.repository.specification.impl.tag;

import com.epam.lab.repository.specification.QuerySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class TagSelectById implements QuerySpecification {
    private static final String SELECT_TAG_BY_ID = "SELECT * FROM task2.tag WHERE id = ?";
    private long id;

    public TagSelectById(long id) {
        this.id = id;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(SELECT_TAG_BY_ID);
            statement.setLong(1, id);
            return statement;
        };
    }
}
