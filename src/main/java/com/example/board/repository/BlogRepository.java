package com.example.board.repository;

import com.example.board.entity.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface BlogRepository extends JpaRepository<BlogPost, Long> {

    @Transactional(readOnly = true)
    List<BlogPost> findByUser_Email(String email);

    Page<BlogPost> findAll(Pageable pageable);

    Page<BlogPost> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(
            String title, String content , Pageable pageable
    );

    Page<BlogPost> findByUser_Email(String st, Pageable pageable);
}
