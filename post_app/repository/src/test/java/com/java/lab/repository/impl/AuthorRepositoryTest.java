package com.java.lab.repository.impl;

import com.java.lab.model.Author;
import com.java.lab.repository.AuthorRepository;
import com.java.lab.repository.configuration.JpaConfig;
import com.java.lab.repository.specification.impl.AuthorSelectAll;
import com.java.lab.repository.specification.impl.AuthorSelectById;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void query() {
        List<Author> authors = authorRepository.query(new AuthorSelectAll());
        assertEquals(3, authors.size());
    }

    @Transactional
    @Test
    public void save() {
        Author author = new Author();
        author.setName("ou");
        author.setSurname("uo");
        authorRepository.save(author);
        List<Author> authors = authorRepository.query(new AuthorSelectAll());
        assertEquals(author, authors.get(3));
    }

    @Transactional
    @Test
    public void remove() {
        authorRepository.remove(1);
        List<Author> authors = authorRepository.query(new AuthorSelectAll());
        assertEquals(2, authors.size());
    }

    @Transactional
    @Test
    public void update() {
        Author author = new Author();
        author.setId(1);
        author.setName("abc");
        author.setSurname("abc");
        authorRepository.update(author);
        List<Author> authors = authorRepository.query(new AuthorSelectById(1));
        assertEquals("abc", authors.get(0).getName());
    }
}