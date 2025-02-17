package com.example.board.repository;

import com.example.board.entity.BlogPost;

import java.util.List;

public interface CustomBlogRepository {
    List<BlogPost> findLimitedBlogs(int limit);
}
