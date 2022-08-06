package com.yuda.book.springboot.domain.posts;

import com.yuda.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity // 실제 DB테이블과 연결될 테이블임을 선언
public class Posts extends BaseTimeEntity {
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 빌더 패턴 클래스 생성 어노테이션(생성자에 선언 시 생성자에 포함된 필드만 빌더에 포함됨)
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}