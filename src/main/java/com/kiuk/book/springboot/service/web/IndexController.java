package com.kiuk.book.springboot.service.web;

import com.kiuk.book.springboot.config.auth.LoginUser;
import com.kiuk.book.springboot.config.auth.dto.SessionUser;
import com.kiuk.book.springboot.service.BookService;
import com.kiuk.book.springboot.service.PostsService;
import com.kiuk.book.springboot.service.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final BookService bookService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
        if(user!=null)
        {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/book")
    public String BookIndex(Model model, @LoginUser SessionUser user)
    {
        model.addAttribute("books", bookService.findAllBookDesc());
        if(user!=null)
        {
            model.addAttribute("userName", user.getName());
        }
        return "book";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }


    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/auth/google/user")
    public @ResponseBody Map<String, Object> userInformation(){
        Map<String, Object> userInfo = new HashMap<>();
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user!=null){
            userInfo.put("userName", user.getName());
            userInfo.put("userEmail", user.getEmail());
            userInfo.put("userPicture", user.getImageUrl());
        }
        return userInfo;
    }

//    // 도서 등록 기능
//    @GetMapping("/book/save")
//    public String BookSave() {return "books-save";}

}
