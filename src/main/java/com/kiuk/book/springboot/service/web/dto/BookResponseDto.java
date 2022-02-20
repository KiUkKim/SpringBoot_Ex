package com.kiuk.book.springboot.service.web.dto;

import com.kiuk.book.springboot.domain.books.Books;

public class BookResponseDto {
    private Long id;
    private String title;
    private String type;
    private String author;
    private String sign;
    private String publish;
    private String shape;

    public BookResponseDto(Books entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.type = entity.getType();
        this.author = entity.getAuthor();
        this.sign = entity.getSign();
        this.publish = entity.getPublish();
        this.shape = entity.getShape();
    }
}
