package NoJobs.BePro.Controller;

import NoJobs.BePro.Domain.Post;
import NoJobs.BePro.Form.PostForm;
import NoJobs.BePro.Form.SearchForm;
import NoJobs.BePro.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {
    private final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/search")
    public List<Post> searchEveryHashtag(@RequestBody SearchForm form){
        return postService.searchByTitleAndTag(form.getInputValue(), form.getHashTags());
    }

    @GetMapping("/get/mainqna")
    public List<Post> GetMainQna(){
        return postService.searchMainQnA();
    }

    @GetMapping("/get/maingongji")
    public List<Post> GetMainNotice(){
        return postService.searchMainNotice();
    }

    @GetMapping("/get/mainviewdec")
    public List<Post> GetMainView(){
        return postService.searchMainView();
    }


    @PostMapping("/qna/post")
    public Map postQna(@RequestBody PostForm form){
        return postService.post(form,"qna");
    }
    @PostMapping("/notice/post")
    public Map postNotice(@RequestBody PostForm form){
        return postService.post(form,"notice");
    }
    @PostMapping("/qna/update")
    public Map updateQna(@RequestBody PostForm form){
        return postService.update(form,"qna");
    }
    @PostMapping("/notice/update")
    public Map updateNotice(@RequestBody PostForm form){
        return postService.update(form,"notice");
    }
}