package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

// 사용자가 요청 -> 응답(HTPL 파일)
// @ Controller


//  사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

    // http://localhost:8080/http/get
    @GetMapping("/http/get")
    public String getTest() {
        return "get 요청";
    }

    // http://localhost:8080/http/post
    @PostMapping("/http/post")
    public String postTest() {
        return "post 요청";
    }

    // http://localhost:8080/http/put
    @PutMapping("/http/put")
    public String putTest() {
        return "put 요청";
    }

    // http://localhost:8080/http/delete
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }
}
