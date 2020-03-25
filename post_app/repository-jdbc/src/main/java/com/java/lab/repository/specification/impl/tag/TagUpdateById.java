package com.epam.lab.repository.specification.impl.tag;

import com.epam.lab.repository.specification.UpdateSpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class TagUpdateById implements UpdateSpecification {
    private static final String UPDATE_TAG_BY_ID = "UPDATE task2.tag SET name = ? WHERE id = ?";
    private long id;
    private String name;

    public TagUpdateById(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(UPDATE_TAG_BY_ID);
            statement.setString(1, name);
            statement.setLong(2, id);
            return statement;
        };
    }
}
