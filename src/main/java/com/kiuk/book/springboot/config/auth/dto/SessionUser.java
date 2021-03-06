package com.kiuk.book.springboot.config.auth.dto;

import com.kiuk.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String imageUrl;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.imageUrl = user.getImageUrl();
    }
}
