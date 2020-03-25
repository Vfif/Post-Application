package com.epam.lab.repository.specification.impl.tag;

import com.epam.lab.repository.specification.QuerySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class TagSelectByName implements QuerySpecification {
    private static final String SELECT_TAG = "SELECT * FROM task2.tag WHERE name = ?";
    private String name;

    public TagSelectByName(String name) {
        this.name = name;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(SELECT_TAG);
            statement.setString(1, name);
            return statement;
        };
    }
}
