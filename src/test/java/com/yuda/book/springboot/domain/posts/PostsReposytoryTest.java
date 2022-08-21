package com.yuda.book.springboot.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsReposytoryTest {
    @Autowired
    PostsRepository postsRepository;

    /*
    * @After: JUnit에서 단위 테스트가 끝날 때 수행되는 메소드
    *         테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용
    *         여러 테스트를 동시해 수행하면 H2에 데이터가 남아있어 다음 테스트 실행 시 실패할 수 있으니 주의
    * */
    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void selectSavedPost() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("yuda")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);
    }
}
