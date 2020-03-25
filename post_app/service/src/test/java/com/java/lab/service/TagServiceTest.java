package com.java.lab.service;

import com.java.lab.dto.TagDto;
import com.java.lab.model.Tag;
import com.java.lab.repository.TagRepository;
import com.java.lab.repository.specification.impl.TagSelectById;
import com.java.lab.repository.specification.impl.TagSelectAll;
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
public class TagServiceTest {

    @Mock
    private TagRepository repository;

    private TagService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new TagService(repository, new ModelMapper());
    }

    @Test
    public void getAllTest() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag());
        when(repository.query(any(TagSelectAll.class))).thenReturn(tags);
        assertEquals(1, service.getAll().size());
    }

    @Test
    public void getByIdTest() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag() {
            {
                setId(10);
            }
        });
        when(repository.query(any(TagSelectById.class))).thenReturn(tags);
        assertEquals(10, service.getById(any(Integer.class)).getId());
    }

    @Test
    public void saveTest() {
        doNothing().when(repository).save(any(Tag.class));
        assertThat(service.save(new TagDto()), is(notNullValue()));
    }

    @Test
    public void updateTest() {
        when(repository.update(any(Tag.class))).thenReturn(new Tag());
        assertThat(service.update(new TagDto(), any(Integer.class)), is(notNullValue()));
    }

    @Test
    public void partUpdate() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag());
        when(repository.query(any(TagSelectById.class))).thenReturn(tags);
        when(repository.update(any(Tag.class))).thenReturn(new Tag());
        assertThat(service.partUpdate(new TagDto(), any(Integer.class)), is(notNullValue()));
    }

    @Test
    public void convertToDtoTest() {
        Tag tag = new Tag();
        tag.setId(12);
        tag.setName("Cat");
        tag.setName("Big");
        TagDto actualTagDto = service.convertToDto(tag);
        TagDto expectedTagDto = new TagDto();
        expectedTagDto.setId(12);
        expectedTagDto.setName("Cat");
        expectedTagDto.setName("Big");
        assertEquals(expectedTagDto, actualTagDto);
    }

    @Test
    public void convertToEntityTest() {
        TagDto tagDto = new TagDto();
        tagDto.setId(12);
        tagDto.setName("Cat");
        tagDto.setName("Big");
        Tag actualTag = service.convertToEntity(tagDto);
        Tag expectedTag = new Tag();
        expectedTag.setId(12);
        expectedTag.setName("Cat");
        expectedTag.setName("Big");
        assertEquals(expectedTag, actualTag);
    }
}