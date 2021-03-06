package com.epam.lab.repository.specification.impl.author;

import com.epam.lab.repository.specification.UpdateSpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class AuthorUpdateSurname implements UpdateSpecification {
    private static final String UPDATE_AUTHOR_BY_ID = "UPDATE task2.author SET surname = ? WHERE id = ?";
    private long id;
    private String surname;

    public AuthorUpdateSurname(long id, String name) {
        this.id = id;
        this.surname = name;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(UPDATE_AUTHOR_BY_ID);
            statement.setString(1, surname);
            statement.setLong(2, id);
            return statement;
        };
    }
}
