package com.back.p67restapi.domain.post.post.controller;

import com.back.p67restapi.domain.post.post.dto.PostDto;
import com.back.p67restapi.domain.post.post.entity.Post;
import com.back.p67restapi.domain.post.post.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.Reader;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostService postService;


//    @GetMapping
//    public List<PostDto> list() {
//        List<Post> postList = postService.findAll();
//
//        List<PostDto> postDtoList = postList.stream()
//                .map(post -> new PostDto(post))
//                .toList();
//
//        return postDtoList;
//    }

//    @GetMapping("/{postId}")
//    public PostDto detail(@PathVariable Long postId) {
//        Post post = postService.findById(postId).get();
//
//        return new PostDto(post);
//    }



}