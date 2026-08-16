package com.back.p67restapi.domain.post.post.entity;

import com.back.p67restapi.domain.post.comment.entity.PostComment;
import com.back.p67restapi.global.jpa.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import com.back.p67restapi.domain.post.comment.entity.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Getter
@Entity
public class Post extends BaseEntity {
    private String title;
    private String content;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval=true,
            fetch = FetchType.LAZY)
    private List<PostComment> comments = new ArrayList<>();

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostComment addComment(String content) {
        PostComment postComment = new PostComment(content, this);
        this.comments.add(postComment);

        return postComment;
    }

    public void removeComment(int id) {
        comments.removeIf(comment -> comment.getId() == id);
    }
}
