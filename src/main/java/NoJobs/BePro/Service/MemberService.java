package NoJobs.BePro.Service;

import NoJobs.BePro.Domain.Member;
import NoJobs.BePro.Repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* 회원가입*/
    public String join(Member member){

        if(!validateDuplicateMember(member)){ //중복회원검증
            return("이미 존재하는 회원입니다.");
        }
        memberRepository.save(member);
        return("success");
    }

    private boolean validateDuplicateMember(Member member) {
        if(memberRepository.findById(member.getId()).isPresent()) {
            return false;
        }
        return true;
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(String memberId){
        return memberRepository.findById(memberId);
    }

}