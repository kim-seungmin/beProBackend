package NoJobs.BePro.Service;

import NoJobs.BePro.Domain.Comment;
import NoJobs.BePro.Form.CommentForm;
import NoJobs.BePro.Repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

public class CommentService {
    private final CommentRepository commnetRepository;

    public CommentService(CommentRepository commnetRepository) {
        this.commnetRepository = commnetRepository;
    }

    public Map update(CommentForm form) {
        return commnetRepository.update(form.getCommentIndex(),form.getCommentDetail());
    }

    public Map insert(CommentForm form){
        return commnetRepository.save(form);
    }

    public Map delete(CommentForm form){
        return commnetRepository.erase(form.getCommentIndex());
    }

}
