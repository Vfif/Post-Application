package com.epam.lab.repository.specification.impl.tag;

import com.epam.lab.repository.specification.QuerySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class TagSelectByPostId implements QuerySpecification {
    private static final String SELECT_TAG_BY_NEWS_ID =
            "SELECT tag.id, tag.name FROM task2.tag " +
                    "JOIN task2.post_tag ON tag.id = tag_id " +
                    "JOIN task2.post ON post.id = post_id " +
                    "WHERE post.id = ?";
    private long id;

    public TagSelectByPostId(long id) {
        this.id = id;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(SELECT_TAG_BY_NEWS_ID);
            statement.setLong(1, id);
            return statement;
        };
    }
}
