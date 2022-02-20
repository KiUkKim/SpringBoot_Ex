package com.kiuk.book.springboot.service.web.dto;

import com.kiuk.book.springboot.domain.user.Role;
import com.kiuk.book.springboot.domain.user.User;
import lombok.Builder;

public class UserDto {
    private String name;
    private String email;
    private String imageUrl;
    private Role role;
    private String token;

    @Builder
    public UserDto(String name, String email, String imageUrl, Role role, String token) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.role=role;
        this.token = token;
    }

    // Dto 객체를 Entity 객체로 변환해서 반환하는 유틸리티 메서드
    public User toEntity() {
        return User.builder().name(name).email(email).imageUrl(imageUrl).role(role).token(token).build();
    }
}
