package com.kiuk.book.springboot.service.web.dto;

import com.kiuk.book.springboot.domain.books.Books;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookListResponseDto {

    private Long id;
    private String title;
    private String type;
    private String author;
    private String sign;
    private String publish;
    private String shape;
    private LocalDateTime modifiedDate;

    public BookListResponseDto(Books B_entity)
    {
        this.id = B_entity.getId();
        this.title = B_entity.getTitle();
        this.type = B_entity.getType();
        this.author = B_entity.getAuthor();
        this.sign = B_entity.getSign();
        this.publish = B_entity.getPublish();
        this.shape = B_entity.getShape();
        this.modifiedDate = B_entity.getModifiedDate();
    }
}
