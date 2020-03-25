package com.java.lab.repository;

import com.java.lab.model.Tag;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepository extends AbstractRepository<Tag> {

    @Override
    protected Tag findById(long id) {
        return getEntityManager().find(Tag.class, id);
    }
}