package com.back.p67restapi.domain.post.comment.dto;

import com.back.p67restapi.domain.post.comment.entity.PostComment;

import java.time.LocalDateTime;

public record PostCommentDto(
        Long id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        String content
) {
    public PostCommentDto(PostComment comment) {
        this(comment.getId(),
        comment.getCreateDate(),
        comment.getModifyDate(),
        comment.getContent()
        );
    }
}
