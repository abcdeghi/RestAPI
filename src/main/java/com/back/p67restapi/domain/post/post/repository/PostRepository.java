package com.back.p67restapi.domain.post.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.back.p67restapi.domain.post.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
