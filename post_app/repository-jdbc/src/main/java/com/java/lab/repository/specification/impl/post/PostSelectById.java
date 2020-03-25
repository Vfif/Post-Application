package com.epam.lab.repository.specification.impl.post;

import com.epam.lab.repository.specification.QuerySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class PostSelectById implements QuerySpecification {
    private static final String SELECT_TAG_BY_ID = "SELECT * FROM task2.post WHERE id = ?";
    private long id;

    public PostSelectById(long id) {
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
