package com.java.lab.model;

import java.util.List;

/**
 * The type Search criteria.
 * Contain parameters used to search.
 */
public class SearchCriteria {
    private Author author;
    private List<Tag> tags;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
