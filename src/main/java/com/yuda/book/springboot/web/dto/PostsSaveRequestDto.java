package com.yuda.book.springboot.web.dto;

import com.yuda.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
* Entity 클래스 != Dto 클래스
* entity 클래스는 데이터베이스와 맞닿은 핵심 클래스로, 이를 기준으로 테이블이 생성되고 스키마가 변경됨
* 따라서 화면을 변경하기 위해 자주 수정되어야 하는 Request/Response 클래스로 사용하기에는 부적절함
* 때문에 Dto 클래스를 따로 만들어 Entity 클래스와 독립적인 Request/Response 클래스로 사용하는 것이 좋음
* */
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
