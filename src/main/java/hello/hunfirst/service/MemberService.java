package hello.hunfirst.service;

import hello.hunfirst.entity.Member;
import hello.hunfirst.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {


    private final MemberRepository memberRepository;

    public Member save(Member member){
        return memberRepository.save(member);
    }

    public Optional<Member> findByUserId(String userId){
        return memberRepository.findByUserId(userId);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public void update(String userId, Member updateParam){
        Optional<Member> MemberOptional = findByUserId(userId);
        if(MemberOptional.isPresent()){
            Member member = MemberOptional.get();
            member.setPassword(updateParam.getPassword());
            memberRepository.save(member);
        }
    }

    public void deleteById(Long id){
        memberRepository.deleteById(id);
    }

}


