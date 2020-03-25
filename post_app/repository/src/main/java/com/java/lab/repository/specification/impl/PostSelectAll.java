package com.java.lab.repository.specification.impl;

import com.java.lab.model.Post;
import com.java.lab.repository.specification.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PostSelectAll implements Specification<Post> {
    @Override
    public CriteriaQuery<Post> specify(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> query = criteriaBuilder.createQuery(Post.class);
        Root<Post> root = query.from(Post.class);
        return query.select(root);
    }
}
