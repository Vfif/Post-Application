package com.epam.lab.repository.impl;

import com.epam.lab.model.Tag;
import com.epam.lab.repository.specification.impl.tag.TagSelectAll;
import com.epam.lab.repository.specification.impl.tag.TagSelectById;
import com.epam.lab.repository.specification.impl.tag.TagUpdateById;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

class TagRepositoryTest {
    TagRepository repository;
    EmbeddedDatabase embeddedDatabase;

    @BeforeEach
    void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addScripts("/tag/schema.sql", "/tag/data.sql")
                .setType(H2)
                .build();
        this.repository = new TagRepository(new JdbcTemplate(embeddedDatabase));
    }
    @Test
    void query() {
        List<Tag> tags = repository.query(new TagSelectAll());
        Assertions.assertEquals(3, tags.size());
    }

    @Test
    void save() {
        Tag tag = new Tag();
        tag.setId(10);
        tag.setName("ou");
        repository.save(tag);
        List<Tag> tags = repository.query(new TagSelectAll());
        Assertions.assertEquals(tag, tags.get(3));
    }

    @Test
    void remove() {
        repository.remove(1);
        List<Tag> tags = repository.query(new TagSelectAll());
        Assertions.assertEquals(2, tags.size());
    }

    @Test
    void update() {
        repository.update(new TagUpdateById(1, "hello"));
        List<Tag> tags = repository.query(new TagSelectById(1));
        Assertions.assertEquals("hello", tags.get(0).getName());
    }

    @AfterEach
    void tearDown() {
        embeddedDatabase.shutdown();
    }
}