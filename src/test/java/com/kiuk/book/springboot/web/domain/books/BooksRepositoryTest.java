package com.kiuk.book.springboot.web.domain.books;

import com.kiuk.book.springboot.domain.books.Books;
import com.kiuk.book.springboot.domain.books.BooksRepository;
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
public class BooksRepositoryTest {

    @Autowired
    BooksRepository booksRepository;

    @After
    public void cleanup(){booksRepository.deleteAll();}

    @Test
    public void 소장물_불러오기()
    {
        //given
        String title = "test";
        String type = "test";
        String author = "test";
        String sign = "test";
        String publish = "test";
        String shape = "test";

        booksRepository.save(Books.builder()
                .title(title)
                .type(type)
                .author(author)
                .sign(sign)
                .publish(publish)
                .shape(shape)
                .build());

        //when
        List<Books> booksList = booksRepository.findAll();

        //then
        Books books = booksList.get(0);
        Assertions.assertThat(books.getTitle()).isEqualTo(title);
        Assertions.assertThat(books.getAuthor()).isEqualTo(author);
    }
}
