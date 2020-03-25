package com.java.lab.controller;

import com.java.lab.dto.AuthorDto;
import com.java.lab.model.Author;
import com.java.lab.service.AuthorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController extends AbstractController<AuthorDto, Author> {
    public AuthorController(AuthorService service) {
        super(service);
    }
}