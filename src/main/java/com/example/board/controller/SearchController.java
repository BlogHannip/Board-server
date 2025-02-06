package com.example.board.controller;

import com.example.board.entity.User;
import com.example.board.service.SearchService;
import com.example.board.service.UserService;
import org.springframework.web.bind.annotation.*;

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
        return searchService.getUserByEmail(email);
    }
}
