package com.java.lab.service;

import com.java.lab.dto.TagDto;
import com.java.lab.exception.EntityNotFoundException;
import com.java.lab.exception.InvalidJsonSaveFormatException;
import com.java.lab.model.Tag;
import com.java.lab.repository.AbstractRepository;
import com.java.lab.repository.specification.impl.TagSelectAll;
import com.java.lab.repository.specification.impl.TagSelectById;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService implements AbstractService<TagDto, Tag> {
    private static Logger logger = LogManager.getLogger();
    private final ModelMapper modelMapper;
    private AbstractRepository<Tag> tagRepository;

    public TagService(AbstractRepository<Tag> tagRepository, ModelMapper modelMapper) {
        this.tagRepository = tagRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TagDto> getAll() {
        List<Tag> tag = tagRepository.query(new TagSelectAll());
        return tag.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TagDto getById(long id) {
        List<Tag> tags = tagRepository.query(new TagSelectById(id));
        if (tags.isEmpty()) {
            logger.error("Post with id = " + id + " not found");
            throw new EntityNotFoundException("Tag with id = " + id + " not found");
        }
        return convertToDto(tags.get(0));
    }

    @Transactional
    @Override
    public TagDto save(TagDto dto) {
        if(dto.getId() != 0) {
            logger.error("Tag shouldn't contain field 'id'");
            throw new InvalidJsonSaveFormatException("Tag shouldn't contain field 'id'");
        }
        Tag tag = convertToEntity(dto);
        tagRepository.save(tag);
        return convertToDto(tag);
    }

    @Transactional
    @Override
    public TagDto update(TagDto dto, long id) {
        Tag tag = convertToEntity(dto);
        tag.setId(id);
        return convertToDto(tagRepository.update(tag));
    }

    @Transactional
    @Override
    public TagDto partUpdate(TagDto dto, long id) {
        return update(dto, id);
    }

    @Transactional
    @Override
    public void delete(long id) {
        tagRepository.remove(id);
    }

    @Override
    public TagDto convertToDto(Tag entity) {
        return modelMapper.map(entity, TagDto.class);
    }

    @Override
    public Tag convertToEntity(TagDto dto) {
        return modelMapper.map(dto, Tag.class);
    }
}
