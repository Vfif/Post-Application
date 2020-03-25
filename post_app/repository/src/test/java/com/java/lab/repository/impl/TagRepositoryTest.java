package com.java.lab.repository.impl;

import com.java.lab.model.Tag;
import com.java.lab.repository.TagRepository;
import com.java.lab.repository.configuration.JpaConfig;
import com.java.lab.repository.specification.impl.TagSelectAll;
import com.java.lab.repository.specification.impl.TagSelectById;
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
public class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void query() {
        List<Tag> tags = tagRepository.query(new TagSelectAll());
        assertEquals(3, tags.size());
    }

    @Transactional
    @Test
    public void save() {
        Tag tag = new Tag();
        tag.setName("ou");
        tagRepository.save(tag);
        List<Tag> tags = tagRepository.query(new TagSelectAll());
        assertEquals(tag, tags.get(3));
    }

    @Transactional
    @Test
    public void remove() {
        tagRepository.remove(1);
        List<Tag> tags = tagRepository.query(new TagSelectAll());
        assertEquals(2, tags.size());
    }

    @Transactional
    @Test
    public void update() {
        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("abc");
        tagRepository.update(tag);
        List<Tag> tags = tagRepository.query(new TagSelectById(1));
        assertEquals("abc", tags.get(0).getName());
    }
}