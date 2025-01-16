package com.example.board.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @PrePersist
    protected void onCreate(){
        this.createAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updateAt = LocalDateTime.now();
    }

}
