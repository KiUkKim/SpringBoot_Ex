package com.kiuk.book.springboot.domain.collectinfo;

import com.kiuk.book.springboot.domain.books.Books;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class CollectInfo extends BaseTimeEntity{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(targetEntity = Books.class, fetch = FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Books book;

    @Column // 위치 정보
    private String collectLocation;

    @Column // 등록 번호
    private String callNumber;

    @Column // 소장 번호
    private String enrollNum;

    // 대출 상태는 소장 권수에 따름
    @Column // 대출 상태 1 이상 -> 대출 가능 , 0 -> 대출 불가능
    private Integer state;

    @Column
    private LocalDate returnDate;

    // 예약 상태는 대출 상태에 따름
    @Column // 대출 상태 0 -> 예약 가능
    private Integer reserveState;

    @Builder
    public CollectInfo(Books book, String collectLocation,
                       String callNumber, String enrollNum,
                       Integer state, LocalDate returnDate, Integer reserveState)
    {
        this.book = book;
        this.collectLocation = collectLocation;
        this.callNumber = callNumber;
        this.enrollNum = enrollNum;
        this.state = state;
        this.returnDate = returnDate;
        this.reserveState = reserveState;
    }
}
