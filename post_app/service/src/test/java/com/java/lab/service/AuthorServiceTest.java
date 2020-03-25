package com.java.lab.service;

import com.java.lab.dto.AuthorDto;
import com.java.lab.model.Author;
import com.java.lab.repository.AuthorRepository;
import com.java.lab.repository.specification.impl.AuthorSelectAll;
import com.java.lab.repository.specification.impl.AuthorSelectById;

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
public class AuthorServiceTest {

    @Mock
    private AuthorRepository repository;

    private AuthorService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new AuthorService(repository, new ModelMapper());
    }

    @Test
    public void getAllTest() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author());
        when(repository.query(any(AuthorSelectAll.class))).thenReturn(authors);
        assertEquals(1, service.getAll().size());
    }

    @Test
    public void getByIdTest() {
        List<Author> authors = new ArrayList<>();
        Author author = new Author();
        author.setId(10);
        authors.add(author);
        when(repository.query(any(AuthorSelectById.class))).thenReturn(authors);
        assertEquals(10, service.getById(any(Integer.class)).getId());
    }

    @Test
    public void saveTest() {
        doNothing().when(repository).save(any(Author.class));
        assertThat(service.save(new AuthorDto()), is(notNullValue()));
    }

    @Test
    public void updateTest() {
        when(repository.update(any(Author.class))).thenReturn(new Author());
        assertThat(service.update(new AuthorDto(), any(Integer.class)), is(notNullValue()));
    }

    @Test
    public void partUpdate() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author());
        when(repository.query(any(AuthorSelectById.class))).thenReturn(authors);
        when(repository.update(any(Author.class))).thenReturn(new Author());
        assertThat(service.partUpdate(new AuthorDto(), any(Integer.class)), is(notNullValue()));
    }

    @Test
    public void convertToDtoTest() {
        Author author = new Author();
        author.setId(12);
        author.setName("Cat");
        author.setName("Big");
        AuthorDto actualAuthorDto = service.convertToDto(author);
        AuthorDto expectedAuthorDto = new AuthorDto();
        expectedAuthorDto.setId(12);
        expectedAuthorDto.setName("Cat");
        expectedAuthorDto.setName("Big");
        assertEquals(expectedAuthorDto, actualAuthorDto);
    }

    @Test
    public void convertToEntityTest() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(12);
        authorDto.setName("Cat");
        authorDto.setName("Big");
        Author actualAuthor = service.convertToEntity(authorDto);
        Author expectedAuthor = new Author();
        expectedAuthor.setId(12);
        expectedAuthor.setName("Cat");
        expectedAuthor.setName("Big");
        assertEquals(expectedAuthor, actualAuthor);
    }
}