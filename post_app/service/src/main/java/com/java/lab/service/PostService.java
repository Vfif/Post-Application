package com.java.lab.service;

import com.java.lab.dto.PostDto;
import com.java.lab.exception.EntityNotFoundException;
import com.java.lab.exception.InvalidJsonSaveFormatException;
import com.java.lab.model.Author;
import com.java.lab.model.Post;
import com.java.lab.model.Tag;
import com.java.lab.repository.AbstractRepository;
import com.java.lab.repository.specification.impl.AuthorSelectByNameAndSurname;
import com.java.lab.repository.specification.impl.PostSelectAll;
import com.java.lab.repository.specification.impl.PostSelectById;
import com.java.lab.repository.specification.impl.TagSelectByName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService implements AbstractService<PostDto, Post> {
    private static Logger logger = LogManager.getLogger();
    private final ModelMapper modelMapper;
    private AbstractRepository<Post> postRepository;
    private AbstractRepository<Author> authorRepository;
    private AbstractRepository<Tag> tagRepository;

    public PostService(ModelMapper modelMapper, AbstractRepository<Post> postRepository,
                       AbstractRepository<Author> authorRepository, AbstractRepository<Tag> tagRepository) {
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
        this.tagRepository = tagRepository;
    }

    public int count() {
        return postRepository.query(new PostSelectAll()).size();
    }

    @Transactional
    @Override
    public List<PostDto> getAll() {
        List<Post> post = postRepository.query(new PostSelectAll());
        return post.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getById(long id) {
        List<Post> post = postRepository.query(new PostSelectById(id));
        if (post.isEmpty()) {
            logger.error("Post with id = " + id + " not found");
            throw new EntityNotFoundException("Post with id = " + id + " not found");
        }
        return convertToDto(post.get(0));
    }

    @Transactional
    @Override
    public PostDto save(PostDto dto) {
        if (dto.getId() != 0) {
            logger.error("Post shouldn't contain field 'id'");
            throw new InvalidJsonSaveFormatException("Post shouldn't contain field 'id'");
        }
        Post post = convertToEntity(dto);
        post.setCreationDate(new Date());
        post.setModificationDate(new Date());
        if (post.getAuthor() != null) {
            savePostAuthor(post);
        }
        if (post.getTags() != null) {
            savePostTags(post);
        }
        postRepository.save(post);
        return convertToDto(post);
    }

    private void savePostAuthor(Post post) {
        Author author = post.getAuthor();
        List<Author> authors = authorRepository.query(new AuthorSelectByNameAndSurname(author));
        if (isNotEmptyAuthors(authors)) {
            post.setAuthor(authors.get(0));
        }
    }

    private void savePostTags(Post post) {
        List<Tag> tagList = post.getTags();
        List<Tag> newTagList = tagList.stream()
                .map(tag -> {
                    List<Tag> tags = tagRepository.query(new TagSelectByName(tag));
                    return tags.isEmpty() ? tag : tags.get(0);
                })
                .collect(Collectors.toList());
        post.setTags(newTagList);
    }

    @Transactional
    @Override
    public PostDto update(PostDto dto, long id) {
        Post post = convertToEntity(dto);
        post.setId(id);
        savePostAuthor(post);
        savePostTags(post);
        return convertToDto(postRepository.update(post));
    }

    @Transactional
    @Override
    public PostDto partUpdate(PostDto dto, long id) {
        Post oldPost = convertToEntity(getById(id));
        Post newPost = convertToEntity(dto);
        Post finalPost = merge(oldPost, newPost);
        return convertToDto(postRepository.update(finalPost));
    }

    private Post merge(Post oldPost, Post newPost) {
        if (newPost.getTitle() != null) {
            oldPost.setTitle(newPost.getTitle());
        }
        if (newPost.getShortText() != null) {
            oldPost.setShortText(newPost.getShortText());
        }
        if (newPost.getFullText() != null) {
            oldPost.setFullText(newPost.getFullText());
        }
        if (newPost.getAuthor() != null) {
            savePostAuthor(newPost);
            oldPost.setAuthor(newPost.getAuthor());
        }
        if (newPost.getTags() != null) {
            savePostTags(newPost);
            oldPost.setTags(newPost.getTags());
        }
        return oldPost;
    }

    @Transactional
    @Override
    public void delete(long id) {
        postRepository.remove(id);
    }

    @Override
    public PostDto convertToDto(Post entity) {
        return modelMapper.map(entity, PostDto.class);
    }

    private boolean isNotEmptyAuthors(List<Author> authors) {
        return !authors.isEmpty();
    }

    @Override
    public Post convertToEntity(PostDto dto) {
        return modelMapper.map(dto, Post.class);
    }
}
