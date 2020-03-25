package com.epam.lab.repository.specification.impl.author;

import com.epam.lab.repository.specification.QuerySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class AuthorSelectByNameSurname implements QuerySpecification {
    private static final String SELECT_AUTHOR = "SELECT * FROM task2.author WHERE name = ? AND surname = ?";
    private String name;
    private String surname;

    public AuthorSelectByNameSurname(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(SELECT_AUTHOR);
            statement.setString(1, name);
            statement.setString(2, surname);
            return statement;
        };
    }
}
