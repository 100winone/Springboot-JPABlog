package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

// 사용자가 요청 -> 응답(HTPL 파일)
// @ Controller

//  사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest : ";
    // http://localhost:8000/blog/http/lombok --> 로 접속해야함
    @GetMapping("/http/lombok")
    public String lombokTest() {
//        Member m = new Member(1, "ssar", "1234", "email");
        Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build(); // builder 패턴 쓰면 이렇게 원하는 것만 넣을 수 있음, 순서를 지킬 필요가 없음
//        System.out.println(TAG+"getter : " + m.getId());
//        m.setId(5000);
//        System.out.println(TAG+"setter : " + m.getId());
        System.out.println(TAG+"getter : " + m.getUsername());
        m.setUsername("cos");
        System.out.println(TAG+"setter : " + m.getUsername());
        return "lombok test 완료";
    }

    // 인터넷 브라우저 요청은 get 요청밖에 할 수 없음
    // http://localhost:8080/http/get
    @GetMapping("/http/get")
//    public String getTest(@RequestParam int id, @RequestParam String username) {
    public String getTest(Member m) { // 위를 아래 Member 객체로 대체 가능, 이렇게 parsing 해주는 것은 MessageConverter(스프링부트)가 해줌 --> 오브젝트에 매핑시켜주는 것
        return "get 요청 " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8080/http/post
    // Body로 받으려면 @ReqeustBody로 받음
    @PostMapping("/http/post") // application/json
    public String postTest(@RequestBody Member m) {
        return "post 요청 " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }
    /* JSON 형식으로 받을 것
    * {
    "id" : 1,
    "username" : "ssar",
    "possword" : 123456,
    "email" : "ssar@nate.com"
    }
    *
    * */

    // http://localhost:8080/http/put
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put 요청 " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8080/http/delete
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }
}
