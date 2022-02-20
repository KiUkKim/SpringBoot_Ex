package com.kiuk.book.springboot.service.web.dto;

import com.kiuk.book.springboot.domain.books.Books;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookSaveRequestDto {
    private String title;
    private String type;
    private String author;
    private String sign;
    private String publish;
    private String shape;

    @Builder
    public BookSaveRequestDto(String title, String type,
                               String author, String sign,
                              String publish, String shape){
        this.title = title;
        this.type = type;
        this.author = author;
        this.sign = sign;
        this.publish = publish;
        this.shape = shape;
    }

    public Books toBookEntity(){
        return Books.builder()
                .title(title)
                .type(type)
                .author(author)
                .sign(sign)
                .publish(publish)
                .shape(shape)
                .build();
    }
}
