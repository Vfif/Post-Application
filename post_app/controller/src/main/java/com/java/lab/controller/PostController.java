package com.java.lab.controller;

import com.java.lab.dto.PostDto;
import com.java.lab.model.Post;
import com.java.lab.service.PostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/post")
public class PostController extends AbstractController<PostDto, Post> {
    public PostController(PostService service) {
        super(service);
    }
}
