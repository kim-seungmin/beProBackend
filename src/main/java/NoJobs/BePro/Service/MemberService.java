package NoJobs.BePro.Service;

import NoJobs.BePro.Domain.Member;
import NoJobs.BePro.Repository.MemberRepository;

import NoJobs.BePro.Tool.SecureTool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* 회원가입*/
    public String join(Member member){
        SecureTool st = new SecureTool();

        if(validateDuplicateMember(member)){ //중복회원검증
            return("이미 존재하는 회원입니다.");
        }
        try {
            member.setPassword(st.makePassword(member.getPassword(), member.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        memberRepository.save(member);
        return("success");
    }

    public boolean validateDuplicateMember(Member member) {
        if(memberRepository.findById(member.getId()).isPresent()) {
            return true;
        }
        return false;
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(String memberId){
        return memberRepository.findById(memberId);
    }

    //로그인

    public Optional<Member> login(Member member) throws Exception {
        SecureTool st = new SecureTool();
        Optional<Member> DBMember = findOne(member.getId());
        Optional<Member> resultMember = Optional.of(member);
        //ID검증
        if(DBMember.isEmpty()){
            resultMember.get().setToken("fail");
            return resultMember;
        }
        resultMember.get().setName(DBMember.get().getName());
        //비밀번호검증
        member.setPassword(st.makePassword(member.getPassword(),member.getId()));
        if(!Objects.equals(member.getPassword(), DBMember.get().getPassword())){
            resultMember.get().setToken("fail");
            return resultMember;
        }
        String token = st.makeJwtToken(member.getId(),member.getPassword());
        if(this.memberRepository.updateToken(member, token)){
            resultMember.get().setToken(token);
            return resultMember;
        }
        resultMember.get().setToken("fail");
        return resultMember;
    }


}