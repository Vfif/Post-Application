package com.epam.lab.repository.specification.impl.post;

import com.epam.lab.repository.specification.QuerySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class PostSelectByTagId implements QuerySpecification {
    private static final String SELECT_TAG_BY_TAG_ID =
            "SELECT * FROM task2.post JOIN task2.post_tag ON post.id = post_id WHERE tag_id = ?";
    private long tagId;

    public PostSelectByTagId(long tagId) {
        this.tagId = tagId;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(SELECT_TAG_BY_TAG_ID);
            statement.setLong(1, tagId);
            return statement;
        };
    }
}
