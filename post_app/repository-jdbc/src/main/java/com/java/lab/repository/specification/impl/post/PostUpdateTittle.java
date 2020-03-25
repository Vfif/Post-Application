package com.epam.lab.repository.specification.impl.post;

import com.epam.lab.repository.specification.UpdateSpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Date;
import java.sql.PreparedStatement;

public class PostUpdateTittle implements UpdateSpecification {
    private static final String UPDATE_NEWS =
            "UPDATE task2.post SET tittle = ?, modification_date = ? WHERE id = ?";
    private long id;
    private String tittle;

    public PostUpdateTittle(long id, String tittle) {
        this.id = id;
        this.tittle = tittle;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(UPDATE_NEWS);
            statement.setString(1, tittle);
            statement.setDate(2, new Date(new java.util.Date().getTime()));
            statement.setLong(3, id);
            return statement;
        };
    }
}
