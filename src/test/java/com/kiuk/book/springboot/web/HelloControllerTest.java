package com.kiuk.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
// 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
@WebMvcTest
@WebAppConfiguration
 // 여러 스프링 테스트 어노테이션 중, WEB (MVC)에 집중할 수 있는 어노테이션

// @Controller, @ControllerAdvice 등은 사용할 수 있지만, @service, @component, @Repository 등은 사용할 수 없다.
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 Bean을 주입받는다.
    private MockMvc mvc; // 웹 API를 테스트할 때 사용

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // mvc를 통해 /hello 주소로 HTTP GET 요청응 한다.
                .andExpect(status().isOk()) // 결과를 검증한다.
                .andExpect(content().string(hello)); // 응답 본문의 내용을 검증
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{

        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                // param은 API 테스트할 떄 사용될 요청 파라미터를 설정 (String만 허용)
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                // jsonPath -> JSON 응답값을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
