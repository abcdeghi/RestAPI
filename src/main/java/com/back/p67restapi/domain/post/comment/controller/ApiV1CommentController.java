package com.back.p67restapi.domain.post.comment.controller;

import com.back.p67restapi.domain.post.comment.dto.PostCommentDto;
import com.back.p67restapi.domain.post.comment.entity.PostComment;
import com.back.p67restapi.domain.post.post.entity.Post;
import com.back.p67restapi.domain.post.post.service.PostService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/comments")
public class ApiV1CommentController {
    private final PostService postService;

    @GetMapping("")
    public List<PostCommentDto> getItems(
            @PathVariable Long postId
    ) {
        Post post = postService.findById(postId).get();

        return post.getComments().stream()
                .map(comment -> new PostCommentDto(comment))
                .toList();
    }

    @GetMapping("/{commentId}")
    public PostCommentDto getItem(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ) {
         Post post = postService.findById(postId).get();

        PostComment postComment = post.findCommentById(commentId);

        return new PostCommentDto(postComment);
    }

    record CommentWriteForm(
            @NotBlank(message = "댓글 내용을 입력해주세요.")
            @Size(min = 2, max = 100, message = "댓글 내용은 2글자 이상 100글자 이하로 입력해주세요.")
            String content
    ) {}

    @GetMapping("/write")
    @Transactional
    public String WrtieComment(
            @PathVariable Long postId,
            @Valid CommentWriteForm commentForm
    ) {
            Post post = postService.findById(postId).get();
            PostComment postComment = postService.writeComment(post, commentForm.content());
            postService.flush();

            return "%d번 댓글이 성공적으로 등록되었습니다.".formatted(postComment.getId());
    }

    @GetMapping("/{commentId}/delete")
    @Transactional
    public String DeleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ) {
        Post post = postService.findById(postId).get();
        PostComment postComment = post.findCommentById(commentId);

        postService.deleteComment(post, postComment.getId());

        return "%d번 댓글이 삭제되었습니다.".formatted(commentId);
    }
    
}
