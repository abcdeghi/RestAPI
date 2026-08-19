package com.back.p67restapi.domain.post.comment.controller;

import com.back.p67restapi.domain.post.comment.dto.PostCommentDto;
import com.back.p67restapi.domain.post.comment.entity.PostComment;
import com.back.p67restapi.domain.post.post.entity.Post;
import com.back.p67restapi.domain.post.post.service.PostService;
import com.back.p67restapi.global.rsData.RsData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @DeleteMapping("/{commentId}")
    @Transactional
    public RsData DeleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ) {
        Post post = postService.findById(postId).get();
        PostComment postComment = post.findCommentById(commentId);

        postService.deleteComment(post, postComment.getId());

        RsData<Void> rsData = new RsData<Void>("204-1"
                , "%d번 댓글이 삭제되었습니다.".formatted(postComment.getId()));

        return rsData;
    }

    record CommenModifyForm(
            @NotBlank(message = "댓글 내용을 입력해주세요.")
            @Size(min = 2, max = 100, message = "댓글 내용은 2글자 이상 100글자 이하로 입력해주세요.")
            String content
    ) {}

    @GetMapping("/{commentId}/modify")
    @Transactional
    public String ModifyComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @Valid CommenModifyForm form
    ) {
        Post post = postService.findById(postId).get();

        PostComment postComment = post.findCommentById(commentId);
        postService.modifyComment(post, postComment.getId(), form.content());

        return "%d번 댓글이 수정되었습니다".formatted(commentId);
    }
}
