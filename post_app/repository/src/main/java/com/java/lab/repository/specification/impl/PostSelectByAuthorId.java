package com.java.lab.repository.specification.impl;

import com.java.lab.model.Post;
import com.java.lab.repository.specification.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PostSelectByAuthorId implements Specification<Post> {
    private long id;

    public PostSelectByAuthorId(long id) {
        this.id = id;
    }

    @Override
    public CriteriaQuery<Post> specify(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> query = criteriaBuilder.createQuery(Post.class);
        Root<Post> root = query.from(Post.class);
        root.join("author");
        return query
                .select(root)
                .where(criteriaBuilder.equal(root.get("id"), id));
    }
}