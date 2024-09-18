package hello.hunfirst.service;

import hello.hunfirst.entity.GeneralMember;
import hello.hunfirst.repository.GeneralMemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GeneralMemberService {


    private final GeneralMemberRepository GeneralRepository;

    public GeneralMember save(GeneralMember member){
        return GeneralRepository.save(member);
    }

    public Optional<GeneralMember> findByUserId(String userId){
        return GeneralRepository.findByUserId(userId);
    }

    public List<GeneralMember> findAll(){
        return GeneralRepository.findAll();
    }

    public void update(String userId, GeneralMember updateParam){
        Optional<GeneralMember> MemberOptional = findByUserId(userId);
        if(MemberOptional.isPresent()){
            GeneralMember member = MemberOptional.get();
            member.setPassword(updateParam.getPassword());
            GeneralRepository.save(member);
        }
    }

    public void deleteById(Long id){
        GeneralRepository.deleteById(id);
    }

}


