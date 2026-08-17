package com.back.p67restapi.domain.post.post.controller;

import com.back.p67restapi.domain.post.post.dto.PostDto;
import com.back.p67restapi.domain.post.post.entity.Post;
import com.back.p67restapi.domain.post.post.service.PostService;
import com.back.p67restapi.global.rsData.RsData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class ApiV1PostController {
    private final PostService postService;

    @GetMapping
    public List<PostDto> list() {
        List<Post> postList = postService.findAll();

        List<PostDto> postDtoList = postList.stream()
                .map(post -> new PostDto(post))
                .toList();

        return postDtoList;
    }

    @GetMapping("/{postId}")
    public PostDto getItem(
            @PathVariable Long postId
    ) {
        Post post = postService.findById(postId).get();
        return new PostDto(post);
    }

    @GetMapping("/{postId}/delete")
    public RsData<PostDto> deleteItem(
            @PathVariable Long postId
    ) {
        Post post = postService.findById(postId).get();

        postService.delete(post);

        RsData<PostDto> rsData = new RsData<PostDto>(
                "204-1",
                "%d번 게시물이 삭제되었습니다".formatted(postId),
                new PostDto(post)
        );

        PostDto postDto = rsData.getData();

        return rsData;
    }

}
