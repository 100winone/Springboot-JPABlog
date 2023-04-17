package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    @GetMapping({"","/"}) // 아무것도 안붙인 경우와 / 붙인 경우 index를 찾아감
    // WEB-INF/views/index.jsp
    public String index() {
        return "index";
    }
}
