package NoJobs.BePro.Controller;

import NoJobs.BePro.Form.CommentForm;
import NoJobs.BePro.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CommentController {
    private final CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/update")
    public Map updateCommnet(@RequestBody CommentForm form){
        return commentService.update(form);
    }

    @PostMapping("/comment/new")
    public Map insertCommnet(@RequestBody CommentForm form){
        return commentService.insert(form);
    }

    @PostMapping("/comment/delete")
    public Map deleteCommnet(@RequestBody CommentForm form){
        return commentService.delete(form);
    }

}
