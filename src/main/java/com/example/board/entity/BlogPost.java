package com.example.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자를 만드는데 같은 패키지내에서 참조
//protected로 설정이유:jpa에서 엔티티 클래스 만들때 프록시 기술을 사용할수있도록 설정하기위함
@Getter
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt; //처음 작성된 시각

    @Column(nullable = false)
    private LocalDateTime updateAt; //수정된 시작

    @PrePersist
    public void onCreate(){
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        this.updateAt = LocalDateTime.now();
    }

    @Builder
    public BlogPost(String title ,String content){
        this.title = title;
        this.content = content;
    }
}
