package com.java.lab.repository.specification.impl;

import com.java.lab.model.Tag;
import com.java.lab.repository.specification.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TagSelectAll implements Specification<Tag> {

    @Override
    public CriteriaQuery<Tag> specify(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> root = query.from(Tag.class);
        return query.select(root);
    }
}
