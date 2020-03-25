package com.java.lab.repository.specification.impl;

import com.java.lab.model.Tag;
import com.java.lab.repository.specification.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TagSelectByName implements Specification<Tag> {
    private Tag tag;

    public TagSelectByName(Tag tag) {
        this.tag = tag;
    }

    @Override
    public CriteriaQuery<Tag> specify(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> root = query.from(Tag.class);
        return query
                .select(root)
                .where(criteriaBuilder.equal(root.get("name"), tag.getName()));
    }
}
