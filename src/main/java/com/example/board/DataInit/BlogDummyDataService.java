package com.example.board.DataInit;

import com.example.board.entity.BlogPost;
import com.example.board.entity.Category;
import com.example.board.entity.User;
import com.example.board.repository.BlogRepository;
import com.example.board.repository.CategoryRepository;
import com.example.board.repository.UserRepository;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
public class BlogDummyDataService {

    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private final Faker faker = new Faker(new Locale("ko"));
    private final Random random = new Random();

    public BlogDummyDataService(BlogRepository blogRepository,CategoryRepository categoryRepository, UserRepository userRepository){
        this.blogRepository =blogRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void generateDummyData(int count) {
        List<BlogPost> posts = new ArrayList<>();
        List<User> users = userRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        if (users.isEmpty() || categories.isEmpty()) {
            throw new IllegalStateException("사용자 또는 카테고리가 없습니다! 더미 데이터를 추가해주세요.");
        }

        int batchsize = 1000; //한번에 1000개씩 저장

        for(int i =0 ; i< count; i++){
            String title = faker.book().title();
            String content = faker.lorem().paragraph(3);
            Category category = categories.get(random.nextInt(categories.size()));  // 랜덤 카테고리
            User randomUser = users.get(random.nextInt(users.size())); //랜덤 사용자

            BlogPost post = new BlogPost(title,content,randomUser,category);
            posts.add(post);

            if(posts.size() >= batchsize){
                blogRepository.saveAll(posts);
                posts.clear();
            }
        }

       if(!posts.isEmpty()){
           blogRepository.saveAll(posts);
       }
        
    }


}
