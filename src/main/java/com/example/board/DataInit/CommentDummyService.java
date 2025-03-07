package com.example.board.DataInit;


import com.example.board.entity.BlogPost;
import com.example.board.entity.Comment;
import com.example.board.entity.User;
import com.example.board.repository.BlogRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
public class CommentDummyService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    private final Faker faker = new Faker(new Locale("ko"));
    private final Random random = new Random();

    @PersistenceContext
    private EntityManager entityManager;

    public CommentDummyService(BlogRepository blogRepository ,UserRepository userRepository, CommentRepository commentRepository) {
        this.blogRepository =blogRepository;
        this.userRepository = userRepository;
        this.commentRepository =commentRepository;
    }

    @Transactional
    public void generateDummyComments(int count) {
        List<User> users = userRepository.findAll();
        List<BlogPost> posts =blogRepository.findAll();

        if(users.isEmpty() || posts.isEmpty()){
            System.out.println("사용자 또는 게시글이 존재하지 않습니다.");
            return;
        }

        List<Comment> commentBatch = new ArrayList<>();

        for(int i = 0; i<count; i++){
            User randomUser = users.get(random.nextInt(users.size()));
            BlogPost randomPost = posts.get(random.nextInt(posts.size()));
            String content = faker.lorem().sentence();

            Comment comment = new Comment();
            comment.setContent(content);
            comment.setUser(randomUser);
            comment.setPost(randomPost);

            commentBatch.add(comment);
            entityManager.persist(comment);

            if((i+1) %1000 ==0) {
                entityManager.flush();
                entityManager.clear();
                System.out.println("현재까지 " + (i+1) +"개 댓글생성");
            }
        }

        entityManager.flush(); //마지막 남은 데이터 flush;
        entityManager.clear();
        System.out.println("총" + count + "개의 댓글이 생성되었습니다!"  );
    }
}
