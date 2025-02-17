//package com.example.board.repository;
//
//import com.example.board.entity.BlogPost;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class CustomBlogRepositoryImpl implements CustomBlogRepository{
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<BlogPost> findLimitedBlogs(int limit) {
//        TypedQuery<BlogPost> query = entityManager.createQuery("SELECT ")
//    }
//}
