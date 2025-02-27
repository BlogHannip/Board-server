package com.example.board.repository;

import com.example.board.entity.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogRepositoryCustom {
    Page<BlogPost> searchByKeyWord(String keyword, Pageable pageable);
}
