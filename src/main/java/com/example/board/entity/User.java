package com.example.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호 자동생성 1,2,3, 인덱스로 구채
    private long id; // 기본키

    @ManyToOne
    private User name;

    public User getName() {
        return name;
    }

    public void setName(User name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false , unique = true)
    private int phoneNumber;

    @Lob //큰 데이터를 데이터베이스에 저장하기위해 사용
    private String content;

    @OneToMany(mappedBy = "BlogPost" , cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


}
