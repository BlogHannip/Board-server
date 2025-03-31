package com.example.board.controller;

import com.example.board.dto.UserDto;
import com.example.board.dto.UserUpdateRequest;
import com.example.board.entity.User;
import com.example.board.repository.UserRepository;
import com.example.board.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@CrossOrigin("http://localhost:5173")
@RestController
@Tag(name = "User API" , description = "사용자 관련 API")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "회원가입", description = "새로운 사용자 등록.")
    @PostMapping("/register") //@RequestBody 자바 객체로 conversion (HttpMessageConverter)
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto ,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        String result = userService.register(userDto); //binding 리절트는 처리 x
        if (result.equals("회원가입 성공")){
            return ResponseEntity.status(201).body(result);
        } else {
            return ResponseEntity.status(400).body(result);
        }
    }

    @Operation(summary = "회원 정보 수정", description = "사용자의 정보를 이메일 기준으로 생성.")
    @PatchMapping("/user/{email}")
    public ResponseEntity<User> updateUser(
            @PathVariable String email,
            @RequestBody UserUpdateRequest request
            ) {
        System.out.println(email);
        User updatedUser = userService.updateUser(email,request);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "회원 탈퇴", description = "사용자를 논리삭제 처리하고 쿠키제거")
    @DeleteMapping("/user/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email , HttpServletResponse response){
        userService.softDeleteUser(email);

        // HttpOnly 쿠키 삭제
        ResponseCookie accessCookie = ResponseCookie.from("accessToken" ,"")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0) // 만료
                .sameSite("Lax")
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken" , "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0) //리프레시도 만료
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE , refreshCookie.toString());

        //프런트에서 삭제할수 있도록 Set-Cookie 응답 설정

        return ResponseEntity.ok("회원가입 탈퇴성공");
    }

    @Operation(summary = "유저 복구" , description = "논리삭제한 유저를 복구한다. (null)")
    @PutMapping("/user/restore/{email}")
    public ResponseEntity<String> restoreUser(@PathVariable String email){
        userService.restoreUser(email);
        return ResponseEntity.ok("회원복구 성공");
    }
}
