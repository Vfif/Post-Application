package com.epam.lab.repository.specification.impl.author;

import com.epam.lab.repository.specification.QuerySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class AuthorSelectByPostId implements QuerySpecification {
    private static final String SELECT_AUTHOR_BY_NEWS_ID =
            "SELECT author.id, author.name, author.surname FROM task2.author " +
                    "JOIN task2.post_author ON author.id = author_id " +
                    "JOIN task2.post ON post.id = post_id " +
                    "WHERE post.id = ?";
    private long id;

    public AuthorSelectByPostId(long id) {
        this.id = id;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(SELECT_AUTHOR_BY_NEWS_ID);
            statement.setLong(1, id);
            return statement;
        };
    }
}
