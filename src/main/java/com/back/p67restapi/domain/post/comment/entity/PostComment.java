package com.back.p67restapi.domain.post.comment.entity;

import com.back.p67restapi.domain.post.post.entity.Post;
import com.back.p67restapi.global.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class PostComment extends BaseEntity {

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public void modify(String content) {
        this.content = content;
    }
}
