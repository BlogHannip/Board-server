//package com.example.board;
//
//import com.example.board.entity.User;
//import com.example.board.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.Optional;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //롤백하라
//public class UserRepoTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    void testFindUser() {
//
//        // given
//
//        //when
//       Optional<User> user1  = userRepository.findByEmail("abc123@gmail.com");
//
//       //then
//        if(user1.isPresent()){
//            System.out.println("굿");
//        } else {
//            System.out.println("fuck");
//        }
//    }
//}
