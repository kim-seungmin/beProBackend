package NoJobs.BePro.Repository;
import NoJobs.BePro.Domain.Post;
import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
    Optional<Post> findBytitle(String title);

    List<Post> findAll();
    List<Post> findBetween(long start, long end);
}
