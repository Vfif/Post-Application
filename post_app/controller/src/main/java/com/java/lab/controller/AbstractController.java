package com.java.lab.controller;

import com.java.lab.dto.Dto;
import com.java.lab.model.BaseEntity;
import com.java.lab.service.AbstractService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

/**
 * The type REST Abstract controller.
 *
 * @param <T> the type parameter extends Dto
 * @param <S> the type parameter extends Entity
 * @author Mariya Gurskaya
 * @see PostController
 * @see AuthorController
 * @see TagController
 * @see UserController
 * @since 1.0
 */

public abstract class AbstractController<T extends Dto, S extends BaseEntity> {
    private String url;
    private AbstractService<T, S> service;

    public AbstractController(AbstractService<T, S> service) {
        this.service = service;
    }

    public AbstractService<T, S> getService() {
        return service;
    }

    /**
     * Gets all entities from database.
     *
     * @return the all entities
     */
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<T> getAll() {
        return service.getAll();
    }


    /**
     * Gets entity by id.
     *
     * @param id the entity id which need to find
     * @return the entity with id
     */
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public T getById(@PathVariable @Min(value = 1) long id) {
        return service.getById(id);
    }

    /**
     * Create new entity in database.
     *
     * @param dto the dto can be without some fields
     * @return the entity which was created in database
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<T> create(
            @RequestBody @Valid T dto, UriComponentsBuilder uriComponentsBuilder) {
        HttpHeaders httpHeaders = new HttpHeaders();
        URI uri = uriComponentsBuilder.path(url).build().toUri();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(service.save(dto), httpHeaders, HttpStatus.CREATED);
    }

    /**
     * Update entity in database.
     * Rewrite entity completely.
     *
     * @param dto the dto
     * @param id  the id
     */
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public T update(@RequestBody T dto, @PathVariable @Min(value = 1) long id) {
        return service.update(dto, id);
    }

    /**
     * Part entity update.
     * Can rewrite some fields of entity with given id.
     *
     * @param dto the dto contain not-null fields that need to rewrite.
     * @param id  the entity id
     */
    @PatchMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public T partUpdate(@RequestBody T dto, @PathVariable @Min(value = 1) long id) {
        return service.partUpdate(dto, id);
    }

    /**
     * Delete entity with given id in database.
     *
     * @param id the entity id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable @Min(value = 1) long id) {
        service.delete(id);
    }
}
