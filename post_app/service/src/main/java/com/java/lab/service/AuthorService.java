package com.java.lab.service;

import com.java.lab.dto.AuthorDto;
import com.java.lab.exception.EntityNotFoundException;
import com.java.lab.exception.InvalidJsonSaveFormatException;
import com.java.lab.model.Author;
import com.java.lab.repository.AbstractRepository;
import com.java.lab.repository.specification.impl.AuthorSelectAll;
import com.java.lab.repository.specification.impl.AuthorSelectById;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Author service.
 */
@Service
public class AuthorService implements AbstractService<AuthorDto, Author> {
    private static Logger logger = LogManager.getLogger();
    private final ModelMapper modelMapper;
    private AbstractRepository<Author> authorRepository;

    /**
     * Instantiates a new Author service.
     *
     * @param authorRepository the author repository
     * @param modelMapper      the model mapper
     */
    public AuthorService(AbstractRepository<Author> authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AuthorDto> getAll() {
        List<Author> author = authorRepository.query(new AuthorSelectAll());
        return author.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDto getById(long id) {
        List<Author> authorList = authorRepository.query(new AuthorSelectById(id));
        if (authorList.isEmpty()) {
            logger.error("Post with id = " + id + " not found");
            throw new EntityNotFoundException("Author with id = " + id + " not found");
        }
        return convertToDto(authorList.get(0));
    }

    @Transactional
    @Override
    public AuthorDto save(AuthorDto dto) {
        if(dto.getId() != 0) {
            logger.error("Author shouldn't contain field 'id'");
            throw new InvalidJsonSaveFormatException("Author shouldn't contain field 'id'");
        }
        Author author = convertToEntity(dto);
        authorRepository.save(author);
        return convertToDto(author);
    }

    @Transactional
    @Override
    public AuthorDto update(AuthorDto dto, long id) {
        Author author = convertToEntity(dto);
        author.setId(id);
        return convertToDto(authorRepository.update(author));
    }

    @Transactional
    @Override
    public AuthorDto partUpdate(AuthorDto dto, long id) {
        Author oldAuthor = convertToEntity(getById(id));
        Author newAuthor = convertToEntity(dto);
        Author finalAuthor = merge(oldAuthor, newAuthor);
        return convertToDto(authorRepository.update(finalAuthor));
    }

    private Author merge(Author oldAuthor, Author newAuthor) {
        if (newAuthor.getName() != null && !newAuthor.getName().isEmpty()) {
            oldAuthor.setName(newAuthor.getName());
        }
        if (newAuthor.getSurname() != null && !newAuthor.getSurname().isEmpty()) {
            oldAuthor.setSurname(newAuthor.getSurname());
        }
        return oldAuthor;
    }

    @Transactional
    @Override
    public void delete(long id) {
        authorRepository.remove(id);
    }

    @Override
    public AuthorDto convertToDto(Author entity) {
        return modelMapper.map(entity, AuthorDto.class);
    }

    @Override
    public Author convertToEntity(AuthorDto dto) {
        return modelMapper.map(dto, Author.class);
    }
}
