package NoJobs.BePro.Repository;
import NoJobs.BePro.Domain.Post;
import NoJobs.BePro.Form.PostForm;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
    Optional<Post> findBytitle(String title);
    List<Post> findBytitleAndTag(String title,String[] tags);
    List<Post> findAll();
    List<Post> findBetween(long start, long end);
    List<Post> findBetween(long start, long end,String category);
    List<Post> findByView(long start, long end, String category);
    void insertPost(PostForm form, String category);
    void updatePost(PostForm form, String category);

    List<Map> findAllIn(String board);
}
