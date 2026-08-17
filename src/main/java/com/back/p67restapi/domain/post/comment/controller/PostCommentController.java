package com.back.p67restapi.domain.post.comment.controller;

import com.back.p67restapi.domain.post.comment.entity.PostComment;
import com.back.p67restapi.domain.post.post.entity.Post;
import com.back.p67restapi.domain.post.post.service.PostService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PostCommentController {

    private final PostService postService;

    record CommentWriteForm(
            @NotBlank(message = "댓글 내용을 입력해주세요")
            @Size(min=2, max=100, message = "댓글 글자수를 맞춰주세요")
            String content
    ){}

    @GetMapping("/posts/{postId}/comments/write")
    @Transactional
    @ResponseBody
    public String write(
            @PathVariable Long postId,
            @Valid CommentWriteForm commentForm
    ) {

        Post post = postService.findById(postId).get();
        PostComment postComment = postService.writeComment(post, commentForm.content());

        postService.flush();

        return "%d번 댓글이 성공적으로 등록되었습니다".formatted(postComment.getId());
    }

    @GetMapping("/posts/{postId}/comments/{commentId}/delete")
    @Transactional
    @ResponseBody
    public String delete(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ) {
        Post post = postService.findById(postId).get();
        postService.deleteComment(post, commentId);

        return "%d번 댓글을 삭제하였습니다.".formatted(commentId);
    }


    record CommentModifyForm(
            @NotBlank(message = "댓글 내용을 입력해주세요")
            @Size(min=2, max = 100, message = "댓글 글자수를 맞춰주세요")
            String content
    ){}

    @GetMapping("/posts/{postId}/comments/{commentId}/modify")
    @Transactional
    @ResponseBody
    public String modify(@PathVariable Long postId,
                         @PathVariable Long commentId,
                         @Valid CommentModifyForm commentForm) {
        Post post = postService.findById(postId).get();
        postService.modifyComment(post, commentId, commentForm.content());

        return "%d번 댓글이 수정되었습니다.".formatted(commentId);
    }
}
