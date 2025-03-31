package com.example.board.controller;

import com.example.board.entity.User;
import com.example.board.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:5173")
@Tag(name = "사용자 찾기", description = " 이메일을 통해 사용자를 찾는다 ")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService){
        this.searchService =searchService;
    }

    @GetMapping("/{email}")
    @Operation(summary = "회원 조회" , description = "이메일을 통한 회원 조회")
    public User getUserByEmail(@PathVariable String email){
        System.out.println("요청된 이메일: " + email);
        return searchService.getUserByEmail(email);
    }
}
