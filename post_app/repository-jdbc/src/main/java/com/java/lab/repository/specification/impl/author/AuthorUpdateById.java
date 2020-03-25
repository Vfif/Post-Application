package com.epam.lab.repository.specification.impl.author;

import com.epam.lab.repository.specification.UpdateSpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class AuthorUpdateById implements UpdateSpecification {
    private static final String UPDATE_TAG_BY_ID = "UPDATE task2.author SET name = ?, surname = ? WHERE id = ?";
    private long id;
    private String name;
    private String surname;

    public AuthorUpdateById(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(UPDATE_TAG_BY_ID);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setLong(3, id);
            return statement;
        };
    }
}
