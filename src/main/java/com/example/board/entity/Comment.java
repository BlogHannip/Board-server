package com.example.board.entity;

import jakarta.persistence.*;

@Entity
public class Comment {  //댓글창
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne
    private User name;

}
