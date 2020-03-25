package com.java.lab.service;

import com.java.lab.dto.AuthorDto;
import com.java.lab.dto.PostDto;
import com.java.lab.model.Post;
import com.java.lab.repository.AuthorRepository;
import com.java.lab.repository.PostRepository;
import com.java.lab.repository.TagRepository;
import com.java.lab.repository.specification.impl.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private TagRepository tagRepository;

    private PostService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new PostService(new ModelMapper(), postRepository, authorRepository, tagRepository);
    }

    @Test
    public void getAllTest() {
        List<Post> post = new ArrayList<>();
        post.add(new Post());
        when(postRepository.query(any(PostSelectAll.class))).thenReturn(post);
        assertEquals(1, service.getAll().size());
    }

    @Test
    public void getByIdTest() {
        List<Post> post = new ArrayList<>();
        post.add(new Post() {
            {
                setId(10);
            }
        });
        when(postRepository.query(any(PostSelectById.class))).thenReturn(post);
        assertEquals(10, service.getById(any(Integer.class)).getId());
    }

    @Test
    public void saveTest() {
        doNothing().when(postRepository).save(any(Post.class));
        when(authorRepository.query(any(AuthorSelectByNameAndSurname.class))).thenReturn(new ArrayList<>());
        when(tagRepository.query(any(TagSelectByName.class))).thenReturn(new ArrayList<>());
        PostDto postDto = new PostDto();
        postDto.setAuthor(new AuthorDto());
        postDto.setTags(new ArrayList<>());
        assertThat(service.save(postDto), is(notNullValue()));
    }

    @Test
    public void updateTest() {
        Post post = new Post();
        post.setTags(new ArrayList<>());
        when(postRepository.update(any(Post.class))).thenReturn(post);
        PostDto postDto = new PostDto();
        postDto.setTags(new ArrayList<>());
        assertThat(service.update(postDto, any(Integer.class)), is(notNullValue()));
    }

    @Test
    public void partUpdate() {
        List<Post> post = new ArrayList<>();
        post.add(new Post());
        when(postRepository.query(any(PostSelectById.class))).thenReturn(post);
        when(postRepository.update(any(Post.class))).thenReturn(new Post());
        assertThat(service.partUpdate(new PostDto(), any(Integer.class)), is(notNullValue()));
    }

    @Test
    public void convertToDtoTest() {
        Post post = new Post();
        post.setId(12);
        post.setTitle("Cat");
        post.setShortText("Big");
        PostDto actualPostDto = service.convertToDto(post);
        PostDto expectedPostDto = new PostDto();
        expectedPostDto.setId(12);
        expectedPostDto.setTitle("Cat");
        expectedPostDto.setShortText("Big");
        expectedPostDto.setTags(new ArrayList<>());
        assertEquals(expectedPostDto, actualPostDto);
    }

    @Test
    public void convertToEntityTest() {
        PostDto postDto = new PostDto();
        postDto.setId(12);
        postDto.setTitle("Cat");
        postDto.setShortText("Big");
        Post actualPost = service.convertToEntity(postDto);
        Post expectedPost = new Post();
        expectedPost.setId(12);
        expectedPost.setTitle("Cat");
        expectedPost.setShortText("Big");
        expectedPost.setTags(null);
        assertEquals(expectedPost, actualPost);
    }


}