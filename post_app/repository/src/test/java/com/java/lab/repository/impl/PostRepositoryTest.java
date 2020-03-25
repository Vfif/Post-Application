package com.java.lab.repository.impl;

import com.java.lab.model.Post;
import com.java.lab.repository.PostRepository;
import com.java.lab.repository.configuration.JpaConfig;
import com.java.lab.repository.specification.impl.PostSelectAll;
import com.java.lab.repository.specification.impl.PostSelectById;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void query() {
        List<Post> postList = postRepository.query(new PostSelectAll());
        assertEquals(3, postList.size());
    }

    @Transactional
    @Test
    public void save() {
        Post post = new Post();
        post.setTitle("123");
        post.setShortText("123");
        post.setFullText("2");
        post.setCreationDate(new Date());
        post.setModificationDate(new Date());
        postRepository.save(post);
        List<Post> postList = postRepository.query(new PostSelectAll());
        assertEquals(post, postList.get(3));
    }

    @Transactional
    @Test
    public void remove() {
        postRepository.remove(1);
        List<Post> postList = postRepository.query(new PostSelectAll());
        assertEquals(2, postList.size());
    }

    @Transactional
    @Test
    public void update() {
        Post post = new Post();
        post.setId(1);
        post.setTitle("123");
        post.setShortText("123");
        post.setFullText("2");
        post.setCreationDate(new Date());
        post.setModificationDate(new Date());
        postRepository.update(post);
        List<Post> postList = postRepository.query(new PostSelectById(1));
        assertEquals("123", postList.get(0).getTitle());
    }
}