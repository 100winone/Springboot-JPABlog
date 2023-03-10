package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
// ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity  // User클래스가 MySql에 테이블이 생성이 된다.
//@DynamicInsert // null인 애들은 제외하고 insert 해줌
public class User {

    @Id // PK가 됨
    @GeneratedValue(strategy = GenerationType.IDENTITY) // numbering 전략. 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; // 시퀀스, auto_increment

    @Column(nullable = false, length = 30, unique = true)
    private String username; // 아이디

    @Column(nullable = false, length = 100) // 해쉬 (비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

//    @ColumnDefault("'user'")
    // DB는 RoleType 이라는 것이 없음
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum을 쓰는게 좋다. // ADMIN, USER

    @CreationTimestamp // 시간이 자동 입력
    private Timestamp createDate;


}
