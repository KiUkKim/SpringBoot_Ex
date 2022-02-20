package com.kiuk.book.springboot.domain.books;

import com.kiuk.book.springboot.domain.collectinfo.CollectInfo;
import com.kiuk.book.springboot.domain.posts.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
// title, thumbnail, type, author, sig, publish, shape, collectinfo(collectLocation, callNumber, enrollNum, state, returnDAte, reserveState)

@Getter
@NoArgsConstructor
@Entity
public class Books extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String type;

    @Column(length = 500, nullable = false)
    private String author;

    @Column(length = 500, nullable = false)
    private String sign;

    @Column(length = 500, nullable = false)
    private String publish;

    @Column(length = 500, nullable = false)
    private String shape;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<CollectInfo> collectInfos;

    @Builder
    public Books(String title, String type, String author, String sign, String publish, String shape){
        this.title = title;
        this.type = type;
        this.author = author;
        this.sign = sign;
        this.publish = publish;
        this.shape = shape;
    }

}
