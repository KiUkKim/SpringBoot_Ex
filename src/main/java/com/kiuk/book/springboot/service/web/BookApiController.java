package com.kiuk.book.springboot.service.web;

import com.kiuk.book.springboot.service.BookService;
import com.kiuk.book.springboot.service.web.dto.BookSaveRequestDto;
import com.kiuk.book.springboot.service.web.dto.CollectInfoSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class BookApiController {

    private final BookService bookService;

    // 소장 도서 검색 기능
    @GetMapping("/book/search")
    public @ResponseBody
    Map<String, Object> search_book(@RequestParam(value="keyword")
                                            String keyword, Model model)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("BookList", bookService.searchBooks(keyword));
        return map;
    }

    // 소장 도서 저장
    @PostMapping("/books/save")
    public Long save(@RequestBody BookSaveRequestDto bookDto)
    {
        return bookService.save(bookDto);
    }

    @PostMapping("/collectinfo/save")
    public Long collectInfoSave(@RequestBody CollectInfoSaveRequestDto collectInfoRequestDto) {
        return bookService.save(collectInfoRequestDto);
    }


}
