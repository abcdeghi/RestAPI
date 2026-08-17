package com.back.p67restapi.domain.post.post.dto;

import com.back.p67restapi.domain.post.post.entity.Post;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record PostDto (
    Long id,
    LocalDateTime createDate,
    LocalDateTime modifyDate,
    String title,
    String content
) {
    public PostDto(Post post) {
        this(
            post.getId(),
                    post.getCreateDate(),
                    post.getModifyDate(),
                    post.getTitle(),
                    post.getContent()
        );
    }

}
