package NoJobs.BePro.Service;

import NoJobs.BePro.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JdbcMemberRepository(dataSource);
    }

    @Bean
    public PostService postService(){return new PostService(postRepository());}

    @Bean
    public PostRepository postRepository() { return new JdbcPostRepository(dataSource);}

    @Bean
    public TagService tagService(){
        return new TagService(tagRepository());
    }

    @Bean
    public TagRepository tagRepository() { return new JdbcTagRepository(dataSource);}

    @Bean
    public CommentService commentService(){
        return new CommentService(commentRepository());
    }

    @Bean
    public CommentRepository commentRepository() { return new JdbcCommentRepository(dataSource);}

}