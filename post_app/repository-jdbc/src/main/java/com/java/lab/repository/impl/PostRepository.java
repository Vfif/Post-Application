package com.epam.lab.repository.impl;

import com.epam.lab.model.Post;
import com.epam.lab.repository.AbstractRepository;
import com.epam.lab.repository.specification.QuerySpecification;
import com.epam.lab.repository.specification.UpdateSpecification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository implements AbstractRepository<Post> {
    private static final String INSERT_NEWS = "INSERT INTO task2.post" +
            "(tittle, short_text, full_text, creation_date, modification_date) VALUES (?,?,?,?,?)";
    private static final String DELETE_NEWS = "DELETE FROM task2.post WHERE id = ?";
    private static final String ID = "id";
    private static final String TITTLE = "tittle";
    private static final String SHORT_TEXT = "short_text";
    private static final String FULL_TEXT = "full_text";
    private static final String CREATION_DATE = "creation_date";
    private static final String MODIFICATION_DATE = "modification_date";

    private JdbcTemplate jdbcTemplate;

    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Post save(Post post) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.
                    prepareStatement(INSERT_NEWS, new String[]{ID, CREATION_DATE, MODIFICATION_DATE});
            statement.setString(1, post.getTittle());
            statement.setString(2, post.getShortText());
            statement.setString(3, post.getFullText());
            statement.setDate(4, new Date(new java.util.Date().getTime()));
            statement.setDate(5, new Date(new java.util.Date().getTime()));
            return statement;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        post.setId((long) keys.get(ID));
        post.setCreationDate((Date) keys.get(CREATION_DATE));
        post.setModificationDate((Date) keys.get(MODIFICATION_DATE));
        return post;
    }

    @Override
    public void remove(long id) {
        jdbcTemplate.update(DELETE_NEWS, id);
    }

    @Override
    public List<Post> query(QuerySpecification specification) {
        return jdbcTemplate.query(specification.specify(), (resultSet, i) -> {
            Post post = new Post();
            post.setId(resultSet.getInt(ID));
            post.setTittle(resultSet.getString(TITTLE));
            post.setShortText(resultSet.getString(SHORT_TEXT));
            post.setFullText(resultSet.getString(FULL_TEXT));
            post.setCreationDate(resultSet.getDate(CREATION_DATE));
            post.setModificationDate(resultSet.getDate(MODIFICATION_DATE));
            return post;
        });
    }

    @Override
    public void update(UpdateSpecification specification) {
        jdbcTemplate.update(specification.specify());
    }
}
