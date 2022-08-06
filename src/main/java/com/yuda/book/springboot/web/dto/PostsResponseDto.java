package com.yuda.book.springboot.web.dto;

import com.yuda.book.springboot.domain.posts.Posts;
import com.yuda.book.springboot.domain.posts.PostsRepository;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
