package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인 됨.

    @ColumnDefault("0")
    private  int count; // 조회수

    @ManyToOne(fetch = FetchType.EAGER) // Board = Many, User = One, EAGER는 바로 가져온다는 뜻, 단건이기 때문
    @JoinColumn(name="userId")
    private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board", fetch=FetchType.EAGER) // "board"는 필드이름,reply 클래스에 있는 Board board 변수
    // LAZY는 필요한 것만 가져온다는 것, 안적어주면 기본전략은 LAZY
    // FK가 아님, mappedBy가 있으면 연관관계의 주인이 아니다.
    // 난 FK가 아니에요, DB에 컬럼을 만들지 마세요
    private List<Reply>  reply; // 하나의 게시글은 여러개의 리플을 가지고 있을 수 있기 때문에 List 필요

    @CreationTimestamp
    private Timestamp createDate;

}