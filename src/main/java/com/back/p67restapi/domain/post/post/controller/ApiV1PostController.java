package com.back.p67restapi.domain.post.post.controller;

import com.back.p67restapi.domain.post.post.entity.Post;
import com.back.p67restapi.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApiV1PostController {
    private final PostService postService;

    @ResponseBody
    @GetMapping("/api/v1/posts")
    @Transactional(readOnly = true)
    public List<Post> list(Model model) {

        return postService.findAll();
    }

}
