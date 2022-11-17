package NoJobs.BePro.Service;

import NoJobs.BePro.Domain.Tag;
import NoJobs.BePro.Repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    //전체태그 가져오기
    public List<Tag> getAll(){
        return tagRepository.findAll();
    }

}
