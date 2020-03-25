package com.java.lab.repository;

import com.java.lab.model.Post;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository extends AbstractRepository<Post> {

    @Override
    protected Post findById(long id) {
        return getEntityManager().find(Post.class, id);
    }
}