package com.yuda.book.springboot.web.dto;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class HelloResponseDtoTest {

    @Test
    public void lombokTest() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        /*
        * assertThat(): assertj 동등 비교 메소드
        *               assertThat()의 파라미터와 isEqualTo()의 파라미터를 비교해 같을 때만 성공
        * https://www.youtube.com/watch?v=zLx_fI24UXM&t=408s 참고
        * */
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
