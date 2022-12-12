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

    //멤버확인
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

        Optional<Member> resultMember = Optional.of(member);
        //ID검증
        Optional<Member> DBMember = findOne(member.getId());
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
        if(this.memberRepository.updateToken(member, Optional.of(token))){
            resultMember.get().setToken(token);
            return resultMember;
        }
        resultMember.get().setToken("fail");
        return resultMember;
    }
    
    //로그아웃
    public boolean logout(Member member) throws Exception {
        if(this.memberRepository.updateToken(member, Optional.empty())){
            return true;
        }
        return false;
    }


    public boolean isEditer(String id, int index) {
        Optional<Member> member = findOne(id);
        if(!member.isPresent())return false;
        return memberRepository.isUploader(member.get().getIdNum(), index);
    }
}