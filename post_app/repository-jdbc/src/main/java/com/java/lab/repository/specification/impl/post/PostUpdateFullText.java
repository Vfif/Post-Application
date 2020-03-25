package com.epam.lab.repository.specification.impl.post;

import com.epam.lab.repository.specification.UpdateSpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Date;
import java.sql.PreparedStatement;

public class PostUpdateFullText implements UpdateSpecification {
    private static final String UPDATE_NEWS =
            "UPDATE task2.post SET full_text = ?, modification_date = ? WHERE id = ?";
    private long id;
    private String fullText;

    public PostUpdateFullText(long id, String fullText) {
        this.id = id;
        this.fullText = fullText;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(UPDATE_NEWS);
            statement.setString(1, fullText);
            statement.setDate(2, new Date(new java.util.Date().getTime()));
            statement.setLong(3, id);
            return statement;
        };
    }
}
