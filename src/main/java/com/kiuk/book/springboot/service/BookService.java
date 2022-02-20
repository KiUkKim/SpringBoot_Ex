package com.kiuk.book.springboot.service;

import com.kiuk.book.springboot.domain.books.Books;
import com.kiuk.book.springboot.domain.books.BooksRepository;
import com.kiuk.book.springboot.domain.collectinfo.CollectInfoRepository;
import com.kiuk.book.springboot.service.web.dto.BookListResponseDto;
import com.kiuk.book.springboot.service.web.dto.BookSaveRequestDto;
import com.kiuk.book.springboot.service.web.dto.CollectInfoSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService{

    private final BooksRepository booksRepository;
    private final CollectInfoRepository collectInfoRepository;

    // 소장 자료 등록
    @Transactional
    public Long save(BookSaveRequestDto bookDto){
        return booksRepository.save(bookDto.toBookEntity()).getId();
    }

    // 소장 자료 전체 조회
    @Transactional(readOnly = true)
    public List<BookListResponseDto> findAllBookDesc(){
        return booksRepository.findAllBookDesc().stream()
                .map(BookListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 소장 자료 검색
    @Transactional
    public List<BookListResponseDto> searchBooks(String keyword)
    {
        return booksRepository.findAllBookSearch(keyword).stream()
                .map(BookListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 소장 자료 삭제
    @Transactional
    public void delete (Long id){
        Books books = booksRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 도서가 없습니다."));
        booksRepository.delete(books);
    }

    @Transactional
    public Long save(CollectInfoSaveRequestDto collectInfoSaveRequestDto) {
        return collectInfoRepository.save(collectInfoSaveRequestDto.toEntity()).getId();
    }

}
