package com.back.p67restapi.domain.post.post.service;

import com.back.p67restapi.domain.post.comment.entity.PostComment;
import com.back.p67restapi.domain.post.post.entity.Post;
import com.back.p67restapi.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post write(String title, String content) {
        Post post = new Post(title, content);
        return postRepository.save(post);
    }

    public void flush() {
        postRepository.flush();
    }

    public long count() {
        return postRepository.count();
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void modify(Post post, String title, String content) {
        post.update(title, content);
    }

    public void writeComment(Post post, String content) {
        post.addComment(content);
    }

    public void deleteComment(Post post, int id) {
        post.removeComment(id);
    }

    public PostComment modifyComment(Post post, int commentId, String content) {
        return post.modifyComment(commentId, content);
    }

//
//    public void modifyComment(Post post, Long commentId, String content) {
//        post.updateComment(commentId, content);
//    }

    public void delete(Post post) {
        postRepository.delete(post);
    }
}
