package NoJobs.BePro.Controller;

import NoJobs.BePro.Domain.Member;
import NoJobs.BePro.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MemberController {

//    private String makeJwtToken(String userId, String nickname) {
//        Date now = new Date();
//        return Jwts.builder()
//                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
//                .setIssuedAt(now)
//                .claim("userId", userId)
//                .claim("nickname", nickname)
//                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
//                .compact();
//    }

    @GetMapping("/user/signout")
    public Map home(String id) {
        Map result = new HashMap<String,Object>();
        result.put("id", id);
        result.put("id2", id);
        result.put("id3", id);
        return result;
    }
    //긁어온거
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/user/signup")
    public Map create(@RequestBody MemberForm form){
        Member member = new Member();
        member.setId(form.getId());
        member.setPassword(form.getPw());
        member.setName(form.getNick());
        member.setEmail(form.getEmail());
        member.setMajor(form.getMajor());

        Map result = new HashMap<String,Object>();
        if(memberService.join(member).equals("success")){
            result.put("signUpSuccess", true);
            result.put("msg", "회원가입에 성공했습니다.");
        }else{
            result.put("signUpSuccess", false);
            result.put("msg", "알 수 없는 이유로 실패했습니다.(ID 중복)");
        }
        return result;
    }

    @RequestMapping("/members/all")
    public List<Member> allList(Model model){
        List<Member> members = memberService.findMembers();
        return members;
//        List<Member> members =  new ArrayList<>();
//        Member a = new Member();
//        a.setId("name");
//        Member b = new Member();
//        b.setId("name");
//        members.add(a);
//        members.add(b);
//        return members;
    }

    @GetMapping("members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
