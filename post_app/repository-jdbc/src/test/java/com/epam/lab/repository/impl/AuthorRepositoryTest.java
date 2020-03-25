package com.epam.lab.repository.impl;

import com.epam.lab.model.Author;
import com.epam.lab.repository.specification.impl.author.AuthorSelectAll;
import com.epam.lab.repository.specification.impl.author.AuthorSelectById;
import com.epam.lab.repository.specification.impl.author.AuthorUpdateName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.util.List;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

class AuthorRepositoryTest {

    AuthorRepository repository;
    EmbeddedDatabase embeddedDatabase;

    @BeforeEach
    void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addScripts("/author/schema.sql", "/author/data.sql")
                .setType(H2)
                .build();
        this.repository = new AuthorRepository(new JdbcTemplate(embeddedDatabase));
    }
    @Test
    void query() {
        List<Author> authors = repository.query(new AuthorSelectAll());
        Assertions.assertEquals(3, authors.size());
    }

    @Test
    void save() {
        Author author = new Author();
        author.setName("ou");
        author.setSurname("uo");
        repository.save(author);
        List<Author> authors = repository.query(new AuthorSelectAll());
        Assertions.assertEquals(author, authors.get(3));
    }

    @Test
    void remove() {
        repository.remove(1);
        List<Author> authors = repository.query(new AuthorSelectAll());
        Assertions.assertEquals(2, authors.size());
    }

    @Test
    void update() {
        repository.update(new AuthorUpdateName(1, "hello"));
        List<Author> authors = repository.query(new AuthorSelectById(1));
        Assertions.assertEquals("hello", authors.get(0).getName());
    }

    @AfterEach
    void tearDown() {
        embeddedDatabase.shutdown();
    }
}