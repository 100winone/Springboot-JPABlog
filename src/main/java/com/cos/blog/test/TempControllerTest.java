package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
    //  컨트롤러는 해당경로 이하에 파일을 리턴
    // http://localhost:8080/blog/temp/home
    @GetMapping("/temp/home")
    public String tempHome() {
        System.out.println("tempHome()");
        // 파일 리턴시 기본경로 : /src/main/resources/static
        // 리턴명 : /home.html로 해줘야 리턴 가능
        // 풀경로 : src/main/resources/static/home.html
        return "/home.html";
    }

    @GetMapping("/temp/jsp")
    public String tempJsp() {
        // prefix : /WEB-INF/views/
        // suffix : .jsp
        // 풀네임 : /WEB-INF/views/test.jsp
        return "test";
    }
}
