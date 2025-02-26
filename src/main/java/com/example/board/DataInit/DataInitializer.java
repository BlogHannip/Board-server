//package com.example.board.DataInit;
//
//import com.example.board.entity.BlogPost;
//import com.example.board.entity.Category;
//import com.example.board.entity.User;
//import com.example.board.repository.BlogRepository;
//import com.example.board.repository.CategoryRepository;
//import com.example.board.repository.UserRepository;
//import com.github.javafaker.Faker;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Random;
//
//@Component
//public class DataInitializer {
//
//    private final BlogRepository blogRepository;
//    private final CategoryRepository categoryRepository;
//    private final UserRepository userRepository;
//    private final Faker faker = new Faker();
//    private final Random random = new Random();
//
//    public DataInitializer(BlogRepository blogRepository , CategoryRepository categoryRepository, UserRepository userRepository){
//        this.blogRepository =blogRepository;
//        this.categoryRepository =categoryRepository;
//        this.userRepository =userRepository;
//    }
//
//    @PostConstruct //Post로 데이터 생성과정
//    @Transactional
//    public void initDummyData() {
//        if(blogRepository.count() >0){
//            System.out.println("더미데이터가 이미 존재합니다.");
//            return;
//        }
//
//        List<Category> categories = categoryRepository.findAll();
//        List<User> users = userRepository.findAll();
//
//        if(categories.isEmpty() || users.isEmpty()) {
//            System.out.println("카테고리 또는 유저 데이터가 없습니다.");
//            return;
//        }
//
//        System.out.println("더미 데이터 생성시작....");
//
//        for(int i =0; i< 100000; i++){
//            String title = faker.lorem().sentence(5);
//            String content = faker.lorem().paragraph(10);
//            User randomUSer = users.get(random.nextInt(users.size()));
//            Category randomCategory = categories.get(random.nextInt(categories.size()));
//
//            BlogPost post = new BlogPost(title,content,randomUSer,randomCategory);
//            post.setViewCount(random.nextInt(1000));//1-1000까지 랜덤조회수
//
//            blogRepository.save(post);
//            if(i% 1000 == 0) {
//                System.out.println(i + "개 데이터 생성완료");
//            }
//        }
//
//    }
//}
