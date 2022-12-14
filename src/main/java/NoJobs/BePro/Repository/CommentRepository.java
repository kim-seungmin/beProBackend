package NoJobs.BePro.Repository;
import NoJobs.BePro.Domain.Comment;
import NoJobs.BePro.Form.CommentForm;

import java.util.Map;

public interface CommentRepository {
    Map save(CommentForm form);
    Map erase(int erase);
    Map update(int index, String detail);
}
