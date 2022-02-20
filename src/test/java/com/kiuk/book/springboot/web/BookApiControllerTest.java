package com.kiuk.book.springboot.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiuk.book.springboot.domain.books.Books;
import com.kiuk.book.springboot.domain.books.BooksRepository;
import com.kiuk.book.springboot.domain.collectinfo.CollectInfo;
import com.kiuk.book.springboot.domain.collectinfo.CollectInfoRepository;
import com.kiuk.book.springboot.service.web.dto.BookSaveRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private CollectInfoRepository collectInfoRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        collectInfoRepository.deleteAll();
        booksRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void Books_등록된다() throws Exception {
        String title = "test";
        String type = "test";
        String author = "test";
        String sign = "test";
        String publish = "test";
        String shape = "test";

        BookSaveRequestDto requestDto = BookSaveRequestDto.builder()
                .title(title)
                .type(type)
                .author(author)
                .sign(sign)
                .publish(publish)
                .shape(shape)
                .build();

        String url = "http://localhost:" +port+ "/books/save";

        //when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Books> all = booksRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
    }

    @Test
    @WithMockUser(roles="USER")
    public void Book_조회() throws Exception {
        // Test를 위해서 Book에 해당하는 db 2개 저장
        String title1 = "test";
        String type1 = "test";
        String author1 = "test";
        String sign1 = "test";
        String publish1 = "test";
        String shape1 = "test";

        String title2 = "test2";
        String type2 = "test2";
        String author2 = "test2";
        String sign2 = "test2";
        String publish2 = "test2";
        String shape2 = "test2";

        // 기존 코드 활용하려고 했으나, 단순 조회 기능만 수행하면 되기 떄문에 두 가지 간단하게 넣어서 조회되는지만 테스트

//        Books books = new Books(title1, type1, author1, sign1, publish1, shape1);
//        booksRepository.save(books);
//
//        Books books2 = new Books(title2, type2, author2, sign2, publish2, shape2);
//        booksRepository.save(books2);

        Books savedBooks = booksRepository.save(Books.builder()
                .title(title1)
                .type(type1)
                .author(author1)
                .sign(sign1)
                .publish(publish1)
                .shape(shape1)
                .build());

        savedBooks = booksRepository.save(Books.builder()
                .title(title2)
                .type(type2)
                .author(author2)
                .sign(sign2)
                .publish(publish2)
                .shape(shape2)
                .build());


        //then
        List<Books> all = booksRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
    }


    @Test
    @WithMockUser(roles = "USER")
    public void Book_조회2() throws Exception{
        // Test를 위해서 Book에 해당하는 db 2개 저장
        String title1 = "test";
        String type1 = "test";
        String author1 = "test";
        String sign1 = "test";
        String publish1 = "test";
        String shape1 = "test";

        String title2 = "test2";
        String type2 = "test2";
        String author2 = "test2";
        String sign2 = "test2";
        String publish2 = "test2";
        String shape2 = "test2";

        Books savedBooks = booksRepository.save(Books.builder()
                .title(title1)
                .type(type1)
                .author(author1)
                .sign(sign1)
                .publish(publish1)
                .shape(shape1)
                .build());

        BookSaveRequestDto requestDto = BookSaveRequestDto.builder()
                .title(title2)
                .type(type2)
                .author(author2)
                .sign(sign2)
                .publish(publish2)
                .shape(shape2)
                .build();


        String url = "http://localhost:" +port+ "/books/save";
       //when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Books> all = booksRepository.findAllBookDesc();
        //assertThat(all.get(0).getTitle()).isEqualTo(title1);
        assertThat(all.size()).isEqualTo(2);
    }


    @Test
    @WithMockUser(roles = "USER")
    public void Book_검색() throws Exception{
        // Test를 위해서 Book에 해당하는 db 2개 저장

        final String KeywordTitle = "test2";


        String title1 = "test";
        String type1 = "test";
        String author1 = "test";
        String sign1 = "test";
        String publish1 = "test";
        String shape1 = "test";

        String title2 = "test2";
        String type2 = "test2";
        String author2 = "test2";
        String sign2 = "test2";
        String publish2 = "test2";
        String shape2 = "test2";

        String title3 = "test2";
        String type3 = "test2";
        String author3 = "test2";
        String sign3 = "test2";
        String publish3 = "test2";
        String shape3 = "test2";

        Books savedBooks = booksRepository.save(Books.builder()
                .title(title1)
                .type(type1)
                .author(author1)
                .sign(sign1)
                .publish(publish1)
                .shape(shape1)
                .build());

        savedBooks = booksRepository.save(Books.builder()
                .title(title2)
                .type(type2)
                .author(author2)
                .sign(sign2)
                .publish(publish2)
                .shape(shape2)
                .build());

        savedBooks = booksRepository.save(Books.builder()
                .title(title3)
                .type(type3)
                .author(author3)
                .sign(sign3)
                .publish(publish3)
                .shape(shape3)
                .build());

//        Long KeywordID = savedBooks.getId();


//        BookSaveRequestDto requestDto = BookSaveRequestDto.builder()
//                .title(title2)
//                .type(type2)
//                .author(author2)
//                .sign(sign2)
//                .publish(publish2)
//                .shape(shape2)
//                .build();


//        String url = "http://localhost:" +port+ "/books/save";
//        http://localhost:8080/book/search?keyword=2

        String url = "http://localhost:" + port + "/book/search?keyword=" + KeywordTitle;

        //when
        mvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        //then
        List<Books> all = booksRepository.findAll();
//        assertThat(all.get(0).getId()).isEqualTo(KeywordID);
        assertThat(all.get(1).getTitle()).contains(KeywordTitle);
    }


    // 소장 자료 추가 정보 테스트
    @Test
    @WithMockUser(roles="USER")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void CollectInfo_등록된다() throws Exception {
        Books savedBooks = booksRepository.save(Books.builder()
                .title("title1")
                .type("type1")
                .author("author1")
                .sign("sign1")
                .publish("publish1")
                .shape("shape1")
                .build());

        String collectLocation="과학도서관/Sci-Info(1층서고)";
        String callNumber="006.78.2019z2";
        String enrollNum="121251508";
        Integer state = 0; // 대출중:0 대출가능:1

        LocalDate returnDate = LocalDate.of(2021,8,17);
        Integer reserveState = 1; // 예약 불가능:0 예약 가능:1

        CollectInfo collectInfo = collectInfoRepository.save(CollectInfo.builder()
                .book(savedBooks)
                .collectLocation(collectLocation)
                .callNumber(callNumber)
                .enrollNum(enrollNum)
                .state(state)
                .returnDate(returnDate)
                .reserveState(reserveState)
                .build());

//        CollectInfoSaveRequestDto requestCollectInfoDto = CollectInfoSaveRequestDto.builder()
//                .book(savedBooks)
//                .collectLocation(collectLocation)
//                .callNumber(callNumber)
//                .enrollNum(enrollNum)
//                .state(state)
//                .returnDate(returnDate)
//                .reserveState(reserveState)
//                .build();

        String url = "http://localhost:" +port+ "/collectinfo/save";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        //when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
//                        .content(objectMapper.writeValueAsString(requestCollectInfoDto)))
                .andExpect(status().isOk());

        //then
        List<CollectInfo> all = collectInfoRepository.findAll();
        assertThat(all.get(0).getBook()).isEqualTo(savedBooks);
        assertThat(all.get(0).getCollectLocation()).isEqualTo(collectLocation);
    }
}
