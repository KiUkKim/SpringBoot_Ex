package com.kiuk.book.springboot.service.web;

import com.kiuk.book.springboot.service.UserService;
import com.kiuk.book.springboot.service.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final HttpSession httpSession;
    private final UserService userService;

//    @GetMapping("/auth/google/user")
//    public @ResponseBody
//    Map<String, Object> userInformation(){
//        Map<String, Object> userInfo = new HashMap<>();
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
//        if(user!=null){
//            userInfo.put("userName", user.getName());
//            userInfo.put("userEmail", user.getEmail());
//            userInfo.put("userPicture", user.getPicture());
//        }
//        return userInfo;
//    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/auth/google/user", consumes = "application/json")
    public Long save(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }
}
