package hello.hunfirst.service;

import hello.hunfirst.entity.General_Member;
import hello.hunfirst.repository.General_MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {


    private final General_MemberRepository memberRepository;

    public General_Member save(General_Member member){
        return memberRepository.save(member);
    }

    public Optional<General_Member> findByUserId(String userId){
        return memberRepository.findByUserId(userId);
    }

    public List<General_Member> findAll(){
        return memberRepository.findAll();
    }

    public void update(String userId, General_Member updateParam){
        Optional<General_Member> MemberOptional = findByUserId(userId);
        if(MemberOptional.isPresent()){
            General_Member member = MemberOptional.get();
            member.setPassword(updateParam.getPassword());
            memberRepository.save(member);
        }
    }

    public void deleteById(Long id){
        memberRepository.deleteById(id);
    }

}


