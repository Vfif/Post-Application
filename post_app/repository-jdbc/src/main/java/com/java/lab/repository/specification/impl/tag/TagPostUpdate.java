package com.epam.lab.repository.specification.impl.tag;

import com.epam.lab.repository.specification.UpdateSpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class TagPostUpdate implements UpdateSpecification {
    private static final String UPDATE_TAG_NEWS =
            "INSERT INTO task2.post_tag(post_id, tag_id) VALUES (?,?)";
    private long postId;
    private long tagId;

    public TagPostUpdate(long postId, long tagId) {
        this.postId = postId;
        this.tagId = tagId;
    }

    @Override
    public PreparedStatementCreator specify() {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement(UPDATE_TAG_NEWS);
            statement.setLong(1, postId);
            statement.setLong(2, tagId);
            return statement;
        };
    }
}
