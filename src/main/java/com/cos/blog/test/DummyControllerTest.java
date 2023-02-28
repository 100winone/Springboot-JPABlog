package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

// html파일이 아니라 data를 리턴해주는 controller
@RestController
public class DummyControllerTest {

    @Autowired // 메모리 올릴때 autowired도 같이 올라감, 스프링컴포넌트 스캔 할때 알아서 띄워줌, 이것이 의존성 주입(DI)
    private UserRepository userRepository;
    // save 함수는 id를 전달하지 않으면 insert를 해주고
    // save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
    // save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요.
    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json 데이터를 요청 => Java
        System.out.println("id : " + id);
        System.out.println("password : " + requestUser.getPassword());
        System.out.println("email  : " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        // userRepositoru.save(user)
        // 더티 체킹
        return null;
    }

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    // 한 페이지당 2건에 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
//        Page<User> users = userRepository.findAll(pageable);
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent(); // json 내에 내용만 return 위해 getContent() 사용
        return users;
    }
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
