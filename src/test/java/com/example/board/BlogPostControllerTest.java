package com.example.board.controller;

import com.example.board.dto.BlogDto;
import com.example.board.entity.BlogPost;
import com.example.board.service.BlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BlogPostController.class)
class BlogPostControllerTest {


    @Autowired
    private MockMvc mockMvc;

    private BlogService blogService;

    private BlogDto testBlogDto;
    private BlogPost testBlogPost;

//    @BeforeEach
//    void setUp() {
//        testBlogDto = new BlogDto("Test Title", "Test Content");
//        testBlogPost = new BlogPost("Test Title", "Test Content", "test@example.com");
//    }

    @Test
    void testCreatePost_Unauthorized() throws Exception {
        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Title\",\"content\":\"Test Content\"}"))
                .andExpect(status().isUnauthorized());
    }

//    @Test
//    @WithMockUser(username = "test@example.com", roles = {"USER"})
//    void testCreatePost_Authorized() throws Exception {
//        when(blogService.savePost(any(), any(), any())).thenReturn(testBlogPost);
//
//        mockMvc.perform(post("/api/posts")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"title\":\"Test Title\",\"content\":\"Test Content\"}"))
//                .andExpect(status().isOk());
//    }
}
