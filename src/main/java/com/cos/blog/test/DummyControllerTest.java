package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;

// html파일이 아니라 data를 리턴해주는 controller
@RestController
public class DummyControllerTest {

    @Autowired // 메모리 올릴때 autowired도 같이 올라감, 스프링컴포넌트 스캔 할때 알아서 띄워줌, 이것이 의존성 주입(DI)
    private UserRepository userRepository;

    // {id] 주소로 파라미터를 전달 받을 수 있음
    // http:// localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // user/4을 찾으면(현재는 회원이 3개만 있기 때문)  내가 데이터베이스에서 못찾아오게 되면 user가 Null이 될 것 아냐?
        // Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해!
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당유저는 없습니다. id : " + id);
            }
        });
        // 요청 : 웹브라우저
        // user 객체 = 자바 오브젝트
        // 변환 (웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
        // 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
        // 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에게 던져줌
        return user;
    }

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
