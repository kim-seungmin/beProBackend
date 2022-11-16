package NoJobs.BePro.Repository;
import NoJobs.BePro.Domain.Member;

import java.util.List;
import java.util.Optional;
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(String id);
    boolean updateToken(Member member, Optional<String> token);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
