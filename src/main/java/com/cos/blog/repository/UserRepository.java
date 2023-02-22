package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//  DAO
// 자동으로 bean 등록이 된다.
// @Repository 생략 가능

public interface UserRepository extends JpaRepository<User, Integer> { // 해당 JPA 레파지토리는 User table관리, 그리고 PK는 숫자야 가르쳐 주는것
}
