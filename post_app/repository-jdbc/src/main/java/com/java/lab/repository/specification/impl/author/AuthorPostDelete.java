package com.epam.lab.repository.specification.impl.author;

import com.epam.lab.repository.specification.UpdateSpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class AuthorPostDelete implements UpdateSpecification {
    private static final String DELETE_AUTHOR_NEWS = "DELETE FROM task2.post_author WHERE post_id = ?";
    private long postId;

    public AuthorPostDelete(long postId) {
        this.postId = postId;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(DELETE_AUTHOR_NEWS);
            statement.setLong(1, postId);
            return statement;
        };
    }
}