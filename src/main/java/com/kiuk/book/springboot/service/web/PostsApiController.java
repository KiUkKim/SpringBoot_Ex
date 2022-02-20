package com.kiuk.book.springboot.service.web;

import com.kiuk.book.springboot.service.PostsService;
import com.kiuk.book.springboot.service.web.dto.PostsResponseDto;
import com.kiuk.book.springboot.service.web.dto.PostsSaveRequestDto;
import com.kiuk.book.springboot.service.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

// PostsService에 의존성을 주입해주지 않게되면, Bean을 생성하지 못해서 NullPointerException이 발생하게 된다. 조심하자!
@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id)
    {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

    // 검색 기능 구현
    @GetMapping("/community/search")
    public @ResponseBody Map<String, Object> search(@RequestParam(value="keyword")
                                                    String keyword, Model model)
    {
        Map<String, Object > map = new HashMap<>();
        map.put("postList", postsService.searchPosts(keyword));
        return map;
    }

//    // 검색 기능 구현
//    @GetMapping("/api/v1/search")
//    public Long list(@RequestParam(value = "keyword") String keyword , Model model)
//    {
//        model.addAttribute("postList", PostsService.searchPosts(keyword));
//        return ""
//    }



}
