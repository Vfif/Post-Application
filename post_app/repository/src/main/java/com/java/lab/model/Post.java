package com.java.lab.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "post")
public class Post extends BaseEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "short_text")
    private String shortText;
    @Column(name = "full_text")
    private String fullText;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "modification_date")
    private Date modificationDate;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private List<Tag> tags = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "post_author",
            joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private Author author;

    public Post() {
    }

    public Post(String title, String shortText, String fullText, Date creationDate, Date modificationDate) {
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public Post(long id, String title, String shortText, String fullText, Date creationDate, Date modificationDate) {
        super(id);
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title) &&
                Objects.equals(shortText, post.shortText) &&
                Objects.equals(fullText, post.fullText) &&
                Objects.equals(creationDate, post.creationDate) &&
                Objects.equals(modificationDate, post.modificationDate) &&
                Objects.equals(tags, post.tags) &&
                Objects.equals(author, post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, shortText, fullText, creationDate, modificationDate, tags, author);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Post.class.getSimpleName() + "[", "]")
                .add("id='" + getId() + "'")
                .add("title='" + title + "'")
                .add("shortText='" + shortText + "'")
                .add("fullText='" + fullText + "'")
                .add("creationDate=" + creationDate)
                .add("modificationDate=" + modificationDate)
                .add("tags=" + tags)
                .add("author=" + author)
                .toString();
    }
}



