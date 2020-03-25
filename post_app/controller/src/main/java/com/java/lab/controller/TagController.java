package com.java.lab.controller;

import com.java.lab.dto.TagDto;
import com.java.lab.model.Tag;
import com.java.lab.service.TagService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tags")
public class TagController extends AbstractController<TagDto, Tag> {
    public TagController(TagService service) {
        super(service);
    }
}