package com.kiuk.book.springboot.config.auth.dto;

import com.kiuk.book.springboot.domain.user.Role;
import com.kiuk.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String imageUrl;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey,
                           String name,
                           String email,
                           String imageUrl){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName,
                                    Map<String, Object> attributes){
        if("naver".equals(registrationId)){
            return ofNaver("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .imageUrl((String) attributes.get("imageUrl"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .imageUrl((String) response.get("imageUrl"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .imageUrl(imageUrl)
                .role(Role.GUEST)
                .build();
    }


}
