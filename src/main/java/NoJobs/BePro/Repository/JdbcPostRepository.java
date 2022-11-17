package NoJobs.BePro.Repository;
import NoJobs.BePro.Domain.Post;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JdbcPostRepository implements PostRepository {
    private final DataSource dataSource;
    public JdbcPostRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Post> findBytitle(String title) {
        return Optional.empty();
    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public List<Post> findBetween(long start, long end) {
        return null;
    }
}
