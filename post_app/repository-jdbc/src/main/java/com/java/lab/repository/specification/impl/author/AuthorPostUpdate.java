package com.epam.lab.repository.specification.impl.author;

import com.epam.lab.repository.specification.UpdateSpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class AuthorPostUpdate implements UpdateSpecification {
    private static final String UPDATE_AUTHOR_NEWS =
            "INSERT INTO task2.post_author(post_id, author_id) VALUES (?,?)";
    private long postId;
    private long authorId;

    public AuthorPostUpdate(long postId, long authorId) {
        this.postId = postId;
        this.authorId = authorId;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(UPDATE_AUTHOR_NEWS);
            statement.setLong(1, postId);
            statement.setLong(2, authorId);
            return statement;
        };
    }
}
