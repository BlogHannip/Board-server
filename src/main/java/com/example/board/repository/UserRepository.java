package com.example.board.repository;

import com.example.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //삭제되지 않은 유저만 조회
    Optional<User> findByEmail(String email);

    //삭제된 유저도 조회하도록 (관리자용)
    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmailIncludingDeleted(String email);
}
