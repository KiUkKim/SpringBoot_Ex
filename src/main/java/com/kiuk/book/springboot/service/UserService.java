package com.kiuk.book.springboot.service;

import com.kiuk.book.springboot.domain.user.UserRepository;
import com.kiuk.book.springboot.service.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService
{
    private final UserRepository userRepository;

    @Transactional
    public Long save(UserDto userDto) {
        return userRepository.save(userDto.toEntity()).getId();
    }
}
