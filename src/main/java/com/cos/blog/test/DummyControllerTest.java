package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    @Autowired // 메모리 올릴때 autowired도 같이 올라감, 스프링컴포넌트 스캔 할때 알아서 띄워줌, 이것이 의존성 주입(DI)
    private UserRepository userRepository;
    // http://localhost:8000/blog/dummy/join
    // http의 body에 username, password, email 데이터를 가지고 요청
    @PostMapping("/dummy/join")
//    public String join( String username, String password, String email){
    public String join( User user){
//        System.out.println("username " + username );
//        System.out.println("password " +  password);
//        System.out.println("email " + email);
        System.out.println("username " + user.getUsername() );
        System.out.println("password " +  user.getPassword());
        System.out.println("email " + user.getUsername());
        System.out.println("id " + user.getId());
        System.out.println("rold " + user.getRole());
        System.out.println("createDate " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
