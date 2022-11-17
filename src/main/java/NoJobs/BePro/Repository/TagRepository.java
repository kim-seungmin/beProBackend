package NoJobs.BePro.Repository;
import NoJobs.BePro.Domain.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {
    Tag save(Tag tag);
    Optional<Tag> findByPostId(Long id);
    Optional<Tag> findByDetail(String detail);
    List<Tag> findAll();
}
