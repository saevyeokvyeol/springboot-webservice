package com.yuda.book.springboot.web;

import com.yuda.book.springboot.domain.posts.Posts;
import com.yuda.book.springboot.domain.posts.PostsRepository;
import com.yuda.book.springboot.web.dto.PostsResponseDto;
import com.yuda.book.springboot.web.dto.PostsSaveRequestDto;
import com.yuda.book.springboot.web.dto.PostsUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
/*
* @SpringBootTest: JPA가 포함된 메소드 테스트 시에 사용하는 어노테이션
*                  @WebMvcTest는 테스트 시 JPA 작동X
* */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    /*
    * JPA가 포함된 API 테스트 시 사용하는 객체
    *
     * */
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void insertPosts() throws Exception {
        // given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("yuda")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, requestDto, Long.class);

        // then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(title);
        Assertions.assertThat((all.get(0).getContent())).isEqualTo(content);
    }

    @Test
    public void updatePosts() throws Exception {
        // given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("yuda")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = testRestTemplate
                .exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        Assertions.assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        Assertions.assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }

    @Test
    public void insertBaseTimeStamp() {
        // given
        LocalDateTime now = LocalDateTime.of(2022,2,2,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("yuda")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println("************ createdDate = " + posts.getCreatedDate() + ", modifiedDate = " + posts.getModifiedDate());

        Assertions.assertThat(posts.getCreatedDate().isAfter(now));
        Assertions.assertThat(posts.getModifiedDate().isAfter(now));
    }
}
