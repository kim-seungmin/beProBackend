package NoJobs.BePro.Controller;

import NoJobs.BePro.Domain.Member;
import NoJobs.BePro.Service.MemberService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/user/signin")
    public Map signIn(@RequestBody MemberForm form) throws Exception {
        Member member = new Member();
        member.setId(form.getId());
        member.setPassword(form.getPw());

        Map result = new HashMap<String,Object>();
        Optional<Member> resultMember = memberService.login(member);
        if(resultMember.isEmpty() ||resultMember.get().getName().equals("fail")){
            result.put("loginSuccess", true);
            result.put("nick", null);
            result.put("msg", "로그인에 실패하였습니다.");
            result.put("cookie", null);
        }else{
            result.put("loginSuccess", false);
            result.put("nick", resultMember.get().getName());
            result.put("msg", "로그인에 성공했습니다.");
            result.put("cookie", resultMember.get().getToken());
        }
        return result;
    }

    @GetMapping("/user/signout")
    public Map signOut(String id) {
        Map result = new HashMap<String,Object>();
        result.put("id", id);
        result.put("id2", id);
        result.put("id3", id);
        return result;
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

    @PostMapping("/user/idcheck")
    public Map idCheck(@RequestBody MemberForm form){
        Member member = new Member();
        member.setId(form.getId());

        Map result = new HashMap<String,Object>();
        if(memberService.validateDuplicateMember(member)){
            result.put("idCheckSuccess", true);
            result.put("msg", "사용해도 좋습니다.");
        }else{
            result.put("idCheckSuccess", false);
            result.put("msg", "중복된 아이디가 존재합니다.");
        }
        return result;
    }
}
