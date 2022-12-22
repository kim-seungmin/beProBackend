package NoJobs.BePro.Controller;

import NoJobs.BePro.Domain.Member;
import NoJobs.BePro.Form.AuthForm;
import NoJobs.BePro.Form.MemberForm;
import NoJobs.BePro.Service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/user/signin")
    public Map signIn(@RequestBody MemberForm form) throws Exception {
        Member member = new Member();
        member.setId(form.getId());
        member.setPassword(form.getPw());

        Map result = new HashMap<String,Object>();
        Optional<Member> resultMember = memberService.login(member);
        if(resultMember==null){
            result.put("loginSuccess", false);
            result.put("nick", null);
            result.put("msg", "로그인에 실패하였습니다.");
            result.put("cookie", null);
            result.put("id", null);
            result.put("email", null);
            result.put("isPro", null);
            result.put("major", null);
            return result;
        }else{
            if(resultMember.get().getName().equals("fail")){
                result.put("loginSuccess", false);
                result.put("nick", null);
                result.put("msg", "로그인에 실패하였습니다.");
                result.put("cookie", null);
                result.put("id", null);
                result.put("email", null);
                result.put("isPro", null);
                result.put("major", null);
                return result;
            }
            result.put("loginSuccess", true);
            result.put("nick", resultMember.get().getName());
            result.put("id", resultMember.get().getName());
            result.put("email", resultMember.get().getName());
            if(resultMember.get().getMajor()==null){
                result.put("isPro", false);
                result.put("major", null);
            }else{
                result.put("isPro", true);
                result.put("major", resultMember.get().getMajor());
            }


            result.put("msg", "로그인에 성공했습니다.");
            result.put("cookie", resultMember.get().getToken());
        }
        return result;
    }

    @PostMapping("/user/signout")
    public Map signOut(@RequestBody MemberForm form) throws Exception {
        Map result = new HashMap<String,Object>();
        Member member = new Member();

        member.setId(form.getId());
        if(memberService.logout(member)){
            result.put("signOutSuccess", true);
            result.put("msg", "로그아웃에 성공했습니다");
        }else{
            result.put("signOutSuccess", false);
            result.put("msg", "알 수 없는 이유로 실패했습니다");
        }
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
        member.setPro(form.getIsPro());

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
        if(!memberService.validateDuplicateMember(member)){
            result.put("idCheckSuccess", true);
            result.put("msg", "사용해도 좋습니다.");
        }else{
            result.put("idCheckSuccess", false);
            result.put("msg", "중복된 아이디가 존재합니다.");
        }
        return result;
    }

    @PostMapping("/auth")
    public Map AuthSwitch(@RequestBody AuthForm form){
        /*System.out.println("------------------");
        System.out.println(form.getId());
        System.out.println(form.getIndex());
        System.out.println(form.getToken());
        System.out.println(form.getIsAdmin());
        System.out.println(form.getIsEdit());
        System.out.println(form.getIsLogin());
        System.out.println("------------------");*/
        Map<String,Object> result = new HashMap();

        if(form.getIsEdit()!=null){
            if(memberService.isEditer(form.getId(),form.getIndex())){
                result.put("editAuth",true);
            }else {
                result.put("editAuth", false);
            }
        }
        if(form.getIsSignin()!=null){
            Member member = memberService.findOne(form.getId()).get();
            if(!member.getToken().isEmpty() && member.getToken().equals(form.getToken())){
                result.put("signinAuth",true);
            }else{
                result.put("signinAuth",false);
            }
        }
        if(form.getIsAdmin()!=null){
            Member member = memberService.findOne(form.getId()).get();
            if(member.getAdmin()==1){
                result.put("adminAuth",true);

            }else{
                result.put("adminAuth",false);
            }
        }
        return result;
    }

    @PostMapping("/user/userupdate")
    public Map updateMember(@RequestBody MemberForm form){
        Map result = new HashMap<String, Object>();
        if(memberService.updateMemberInfo(form)){
            result.put("msg","업데이트에 성공했습니다.");
            return result;
        }
        result.put("msg","업데이트에 실패했습니다.");
        return result;
    }


}
