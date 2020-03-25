package com.epam.lab.repository.specification.impl.post;

import com.epam.lab.repository.specification.QuerySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class PostSelectByAuthorId implements QuerySpecification {
    private static final String SELECT_TAG_BY_AUTHOR_ID =
            "SELECT * FROM task2.post JOIN task2.post_author ON post.id = post_id WHERE author_id = ?";
    private long authorId;

    public PostSelectByAuthorId(long authorId) {
        this.authorId = authorId;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(SELECT_TAG_BY_AUTHOR_ID);
            statement.setLong(1, authorId);
            return statement;
        };
    }
}
