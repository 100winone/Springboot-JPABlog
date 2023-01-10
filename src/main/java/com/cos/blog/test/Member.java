package com.cos.blog.test;

import lombok.*;

//@Getter
//@Setter
@Data // Getter와 Setter 동시
//@AllArgsConstructor // 생성자 만들어짐
@NoArgsConstructor // 빈생성자
//@RequiredArgsConstructor // final 붙은애들 생성자를 만들어줌, ex) private final int id;
public class Member {
    // 불변성 유지하기 위해 final 씀 why? 데이터베이스에서 가져온 값을 변경할일이 없으면
    // But, 변경할 일이 있다면?
    private int id;
    private String username;
    private String password;
    private String email;

    @Builder
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
