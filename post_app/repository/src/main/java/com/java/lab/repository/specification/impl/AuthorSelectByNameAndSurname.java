package com.java.lab.repository.specification.impl;

import com.java.lab.model.Author;
import com.java.lab.repository.specification.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AuthorSelectByNameAndSurname implements Specification<Author> {
    private Author author;

    public AuthorSelectByNameAndSurname(Author author) {
        this.author = author;
    }

    @Override
    public CriteriaQuery<Author> specify(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> query = criteriaBuilder.createQuery(Author.class);
        Root<Author> root = query.from(Author.class);
        return query
                .select(root)
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("name"), author.getName())),
                        criteriaBuilder.equal(root.get("surname"), author.getSurname()));
    }
}