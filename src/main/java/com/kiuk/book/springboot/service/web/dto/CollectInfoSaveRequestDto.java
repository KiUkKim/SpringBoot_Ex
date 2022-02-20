package com.kiuk.book.springboot.service.web.dto;


import com.kiuk.book.springboot.domain.books.Books;
import com.kiuk.book.springboot.domain.collectinfo.BaseTimeEntity;
import com.kiuk.book.springboot.domain.collectinfo.CollectInfo;
import com.sun.istack.NotNull;
import lombok.Builder;

import java.time.LocalDate;

public class CollectInfoSaveRequestDto extends BaseTimeEntity {
    private Books book;
    private String collectLocation;
    private String callNumber;
    private String enrollNum;
    private Integer state;
    private LocalDate returnDate;
    private Integer reserveState;

    @Builder
    public CollectInfoSaveRequestDto(Books book, String collectLocation,
                                     String callNumber, String enrollNum,
                                     Integer state, LocalDate returnDate,
                                     Integer reserveState)
    {
        this.book = book;
        this.collectLocation = collectLocation;
        this.callNumber = callNumber;
        this.enrollNum = enrollNum;
        this.state = state;
        this.returnDate = returnDate;
        this.reserveState = reserveState;
    }

    @NotNull
    public CollectInfo toEntity() {
        return CollectInfo.builder()
                .book(book)
                .collectLocation(collectLocation)
                .callNumber(callNumber)
                .enrollNum(enrollNum)
                .state(state)
                .returnDate(returnDate)
                .reserveState(reserveState)
                .build();
    }
}
