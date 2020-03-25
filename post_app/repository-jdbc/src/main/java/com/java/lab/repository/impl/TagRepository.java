package com.epam.lab.repository.impl;

import com.epam.lab.model.Tag;
import com.epam.lab.repository.AbstractRepository;
import com.epam.lab.repository.specification.QuerySpecification;
import com.epam.lab.repository.specification.UpdateSpecification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class TagRepository implements AbstractRepository<Tag> {
    private static final String INSERT_TAG = "INSERT INTO task2.tag(name) VALUES (?)";
    private static final String DELETE_TAG = "DELETE FROM task2.tag WHERE id = ?";
    private static final String ID = "id";
    private static final String NAME = "name";
    private JdbcTemplate jdbcTemplate;

    public TagRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag save(Tag entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.
                    prepareStatement(INSERT_TAG, new String[]{ID});
            statement.setString(1, entity.getName());
            return statement;
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
        return entity;
    }

    @Override
    public void remove(long id) {
        jdbcTemplate.update(DELETE_TAG, id);
    }

    @Override
    public List<Tag> query(QuerySpecification specification) {
        return jdbcTemplate.query(specification.specify(), (resultSet, i) -> {
            Tag tag = new Tag();
            tag.setId(resultSet.getInt(ID));
            tag.setName(resultSet.getString(NAME));
            return tag;
        });
    }

    @Override
    public void update(UpdateSpecification specification) {
        jdbcTemplate.update(specification.specify());
    }
}