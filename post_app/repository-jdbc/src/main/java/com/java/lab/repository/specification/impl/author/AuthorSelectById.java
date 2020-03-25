package com.epam.lab.repository.specification.impl.author;

import com.epam.lab.repository.specification.QuerySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class AuthorSelectById implements QuerySpecification {
    private static final String SELECT_AUTHOR_BY_ID = "SELECT * FROM task2.author WHERE id = ?";
    private long id;

    public AuthorSelectById(long id) {
        this.id = id;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(SELECT_AUTHOR_BY_ID);
            statement.setLong(1, id);
            return statement;
        };
    }
}
