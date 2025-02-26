package com.example.board.service;

import com.example.board.entity.BlogPost;
import com.example.board.repository.BlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchBlogService {

    private final BlogRepository blogRepository;

    public SearchBlogService(BlogRepository blogRepository){
        this.blogRepository =blogRepository;
    }

    public List<BlogPost> getBlogsByUser(String email) {
        return blogRepository.findByUser_Email(email);
    }

    public BlogPost getBlogsById(Long id){
        Optional<BlogPost> blogPost = blogRepository.findById(id);
        return blogPost.orElse(null);
    }

    public Page<BlogPost> searchBlogs(String keyword , Pageable pageable) {
        System.out.println("검색요청: keyword=" +keyword);
        Page<BlogPost> results = blogRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword,keyword,pageable);
        System.out.println("검색 결과 개수:" + results.getTotalElements()); //로그 추가
        return results;
    }

    public Page<BlogPost> getAllBlogs(Pageable pageable){
        return blogRepository.findAll(pageable);
    }
}
