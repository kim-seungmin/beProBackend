package NoJobs.BePro.Controller;

import NoJobs.BePro.Domain.Tag;
import NoJobs.BePro.Form.TagForm;
import NoJobs.BePro.Service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TagController {
    private final TagService tagService;
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/get/hashtag")
    public List<Map<String,String>> searchEveryHashtag(){
        List<Tag> tagList = tagService.getAll();
        List<Map<String,String>> result = new ArrayList<>();
        Map<String, String> tempMap = new HashMap<>();

        for(int i=0;i<tagList.size();i++){
            tempMap.put("name",tagList.get(i).getDetail());
            result.add(tempMap);
        }
        return result;

    }

}
