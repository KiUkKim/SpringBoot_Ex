package com.kiuk.book.springboot.domain.user;

import com.kiuk.book.springboot.domain.posts.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false)
//    private String email;
//
//    @Column
//    private String picture;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;
//
//    @Builder
//    public User(String name, String email, String picture, Role role)
//    {
//        this.name = name;
//        this.email = email;
//        this.picture = picture;
//        this.role = role;
//    }
//
//    public User update(String name, String picture)
//    {
//        this.name = name;
//        this.picture = picture;
//
//        return this;
//    }
//
//    public String getRoleKey(){
//        return this.role.getKey();
//    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "token", columnDefinition = "TEXT")
    private String token;

    @Builder
    public User(String name, String email, String imageUrl, Role role, String token){
        this.name=name;
        this.email=email;
        this.imageUrl=imageUrl;
        this.role=role;
        this.token=token;
    }

    public User update(String name, String picture){
        this.name = name;
        this.imageUrl = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
