package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//  DAO
// 자동으로 bean 등록이 된다.
// @Repository 생략 가능

public interface UserRepository extends JpaRepository<User, Integer> { // 해당 JPA 레파지토리는 User table관리, 그리고 PK는 숫자야 가르쳐 주는것

}

// JPA Naming 쿼리
// SELECT * FROM user WHERE username = ? AND password = ?; 쿼리가 동작됨
//    User findByUsernameAndPassword(String username, String password);

// 아래와 같이 native 쿼리 형태로도 사용 가능
//    @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//    User login(String username, String password);