package com.back.p67restapi.domain.post.comment.controller;

import com.back.p67restapi.domain.post.post.entity.Post;
import com.back.p67restapi.domain.post.post.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import com.back.p67restapi.domain.post.comment.entity.Comment;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final PostService postService;

    record CommentWriteForm(
            @NotBlank(message = "댓글 내용을 입력해주세요.")
            @Size(min = 2, max = 100, message = "댓글 내용은 2글자 이상 100글자 이하로 입력해주세요.")
            String content
    ) {}

//    @PostMapping("/posts/{postId}/comments/write")
//    @Transactional
//    public String write(
//            @PathVariable int postId,
//            @Valid CommentWriteForm form
//    ) {
//        Post post = postService.findById(postId).get();
//
//        postService.writeComment(post, form.content);
//        return "redirect:/posts/" + postId;
//    }


    record CommentModifyForm(
            @NotBlank(message = "댓글 내용을 입력해주세요.")
            @Size(min = 2, max = 100, message = "댓글 내용은 2글자 이상 100글자 이하로 입력해주세요.")
            String content
    ) {}






}