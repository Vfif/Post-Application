package com.epam.lab.repository.specification.impl.post;

import com.epam.lab.repository.specification.UpdateSpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Date;
import java.sql.PreparedStatement;

public class PostUpdate implements UpdateSpecification {
    private static final String UPDATE_NEWS =
            "UPDATE task2.post SET tittle = ?, short_text = ?, full_text = ?, modification_date = ? WHERE id = ?";
    private long id;
    private String tittle;
    private String shortText;
    private String fullText;

    public PostUpdate(long id, String tittle, String shortText, String fullText) {
        this.id = id;
        this.tittle = tittle;
        this.shortText = shortText;
        this.fullText = fullText;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(UPDATE_NEWS);
            statement.setString(1, tittle);
            statement.setString(2, shortText);
            statement.setString(3, fullText);
            statement.setDate(4, new Date(new java.util.Date().getTime()));
            statement.setLong(5, id);
            return statement;
        };
    }
}
