package NoJobs.BePro.Controller;

import NoJobs.BePro.Domain.Post;
import NoJobs.BePro.Form.ListForm;
import NoJobs.BePro.Form.MemberForm;
import NoJobs.BePro.Form.PostForm;
import NoJobs.BePro.Form.SearchForm;
import NoJobs.BePro.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/board/main")
    public List<Post> GetMain(@RequestBody ListForm form){
        if(form.getBoard().equals("qna")){
            return postService.searchMainQnA();
        }else{
            return postService.searchMainNotice();
        }
    }

    @PostMapping("/board/list")
    public List<Map> GetPostList(@RequestBody ListForm form){
        return  postService.searchList(form.getBoard());
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

    @PostMapping("/mypage/getpostings")
    public List<Map> findMemberPost(@RequestBody MemberForm form){
        return postService.getOnesPost(form.getId());
    }

    @PostMapping("/board/view")
    public Map findById(@RequestBody PostForm form){
        return postService.getPostById(form.getId());
    }

}
