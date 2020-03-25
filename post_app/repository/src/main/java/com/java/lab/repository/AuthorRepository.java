package com.java.lab.repository;

import com.java.lab.model.Author;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepository extends AbstractRepository<Author> {

    @Override
    protected Author findById(long id) {
        return getEntityManager().find(Author.class, id);
    }
}