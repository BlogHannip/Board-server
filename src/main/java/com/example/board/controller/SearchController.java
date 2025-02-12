package com.example.board.controller;

import com.example.board.entity.User;
import com.example.board.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:5173")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService){
        this.searchService =searchService;
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email){
        System.out.println("요청된 이메일: " + email);
        return searchService.getUserByEmail(email);
    }
}
