package com.example.board.repository;

import com.example.board.entity.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface BlogRepository extends JpaRepository<BlogPost, Long> ,BlogRepositoryCustom {

    @Transactional(readOnly = true)
    List<BlogPost> findByUser_Email(String email);

    Page<BlogPost> findAll(Pageable pageable);

    Page<BlogPost> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(
            String title, String content , Pageable pageable
    );
    //Containing(%keyword%) //특정 단어 포함
    //EndWith(%keyword) 끝나는 단어
    //StartWith(keyword%) //시작 단어
    //like(like :keyword) 유사한 단어

    Page<BlogPost> findByUser_Email(String st, Pageable pageable);
}
