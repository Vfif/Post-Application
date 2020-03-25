package com.epam.lab.repository.impl;

import com.epam.lab.model.Post;
import com.epam.lab.repository.specification.impl.post.PostSelectAll;
import com.epam.lab.repository.specification.impl.post.PostSelectById;
import com.epam.lab.repository.specification.impl.post.PostUpdateTittle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.util.List;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

class PostRepositoryTest {
    PostRepository repository;
    EmbeddedDatabase embeddedDatabase;

    @BeforeEach
    void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addScripts("/post/schema.sql", "/post/data.sql")
                .setType(H2)
                .build();
        this.repository = new PostRepository(new JdbcTemplate(embeddedDatabase));
    }

    @Test
    void query() {
        List<Post> post = repository.query(new PostSelectAll());
        Assertions.assertEquals(3, post.size());
    }

    @Test
    void save() {
        Post post = new Post();
        post.setTittle("ou");
        post.setShortText("uo");
        post.setFullText("uo");
        repository.save(post);
        List<Post> list = repository.query(new PostSelectAll());
        Assertions.assertEquals(post, list.get(3));
    }

    @Test
    void remove() {
        repository.remove(1);
        List<Post> post = repository.query(new PostSelectAll());
        Assertions.assertEquals(2, post.size());
    }

    @Test
    void update() {
        repository.update(new PostUpdateTittle(1, "hello"));
        List<Post> post = repository.query(new PostSelectById(1));
        Assertions.assertEquals("hello", post.get(0).getTittle());
    }

    @AfterEach
    void tearDown() {
        embeddedDatabase.shutdown();
    }
}