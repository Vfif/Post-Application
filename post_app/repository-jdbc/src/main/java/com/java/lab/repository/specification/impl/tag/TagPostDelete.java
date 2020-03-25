package com.epam.lab.repository.specification.impl.tag;

import com.epam.lab.repository.specification.UpdateSpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class TagPostDelete implements UpdateSpecification {
    private static final String DELETE_TAG_NEWS = "DELETE FROM task2.post_tag WHERE post_id = ?";
    private long postId;

    public TagPostDelete(long postId) {
        this.postId = postId;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(DELETE_TAG_NEWS);
            statement.setLong(1, postId);
            return statement;
        };
    }
}