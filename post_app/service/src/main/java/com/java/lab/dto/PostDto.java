package com.java.lab.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PostDto extends Dto {
    @NotNull
    @Size(max = 20)
    private String title;

    @NotNull
    @Size(max = 100)
    private String shortText;

    @NotNull
    @Size(max = 200)
    private String fullText;


    @DateTimeFormat(pattern="dd-MMM-yyyy")
    private Date creationDate;


    @DateTimeFormat(pattern="dd-MMM-yyyy")
    private Date modificationDate;

    private AuthorDto author;
    private List<TagDto> tags;

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

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "title='" + title + '\'' +
                ", shortText='" + shortText + '\'' +
                ", fullText='" + fullText + '\'' +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                ", author=" + author +
                ", tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PostDto postDto = (PostDto) o;
        return Objects.equals(title, postDto.title) &&
                Objects.equals(shortText, postDto.shortText) &&
                Objects.equals(fullText, postDto.fullText) &&
                Objects.equals(creationDate, postDto.creationDate) &&
                Objects.equals(modificationDate, postDto.modificationDate) &&
                Objects.equals(author, postDto.author) &&
                Objects.equals(tags, postDto.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, shortText, fullText, creationDate, modificationDate, author, tags);
    }
}
