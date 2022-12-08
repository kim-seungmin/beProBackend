package NoJobs.BePro.Service;

import NoJobs.BePro.Domain.Post;
import NoJobs.BePro.Form.PostForm;
import NoJobs.BePro.Repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public class PostService {
    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {this.postRepository = postRepository;}

    public List<Post> searchByTitleAndTag(String inputValue, String[] hashTags) {
        return postRepository.findBytitleAndTag(inputValue,hashTags);
    }
    public List<Post> searchMainQnA(){
        return postRepository.findBetween(0,10,"qna");
    }
    public List<Post> searchMainNotice(){
        return postRepository.findBetween(0,10,"notice");
    }

    public List<Post> searchMainView() {
        return postRepository.findByView(0, 10, "qna");
    }

    public Map post(PostForm form, String category) {
        postRepository.insertPost(form, category);
        return(Map.of("msg","글이 저장되었습니다"));
    }

    public Map update(PostForm form, String category) {
        postRepository.updatePost(form, category);
        return(Map.of("msg","글이 수정되었습니다"));
    }
}
