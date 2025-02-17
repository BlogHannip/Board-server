package com.example.board.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
//protected로 설정이유:jpa에서 엔티티 클래스 만들때 프록시 기술을 사용할수있도록 설정하기위함
@Getter
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    public BlogPost(){
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",name = "content", nullable = false)
    private String content;

    @Column(nullable = false, updatable = false , columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt; //처음 작성된 시각

    @Column(nullable = false)
    private LocalDateTime updatedAt; //수정된 시작

    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    //사용자와 연관관계
    @ManyToOne //여러개의 블로그 글들을 한명의 사용자를 참조한다.
    @JoinColumn(name = "user_email", referencedColumnName = "email")//이메일 외래키로 참조
    private User user;

    public BlogPost(String title, String content, User user){
        this.title =title;
        this.content =content;
        this.user =user;
    }

    @JsonProperty("id")
    public Long getId(){
        return id;
    }
}
