package com.epam.lab.repository.impl;

import com.epam.lab.model.Author;
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
public class AuthorRepository implements AbstractRepository<Author> {
    private static final String INSERT_AUTHOR = "INSERT INTO task2.author(name, surname) VALUES (?,?)";
    private static final String DELETE_AUTHOR = "DELETE FROM task2.author WHERE id = ?";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";

    private JdbcTemplate jdbcTemplate;

    public AuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author save(Author entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.
                    prepareStatement(INSERT_AUTHOR, new String[]{ID});
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            return statement;
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
        return entity;
    }

    @Override
    public void remove(long id) {
        jdbcTemplate.update(DELETE_AUTHOR, id);
    }

    @Override
    public List<Author> query(QuerySpecification specification) {
        return jdbcTemplate.query(specification.specify(), (resultSet, i) -> {
            Author author = new Author();
            author.setId(resultSet.getInt(ID));
            author.setName(resultSet.getString(NAME));
            author.setSurname(resultSet.getString(SURNAME));
            return author;
        });
    }

    @Override
    public void update(UpdateSpecification specification) {
        jdbcTemplate.update(specification.specify());
    }
}
