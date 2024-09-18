package hello.hunfirst.service;

import hello.hunfirst.entity.OwnerMember;
import hello.hunfirst.repository.OwnerMemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerMemberService {


    private final OwnerMemberRepository ownerMemberRepository;

    public OwnerMember save(OwnerMember member){
        return ownerMemberRepository.save(member);
    }

    public Optional<OwnerMember> findByUserId(String userId){
        return ownerMemberRepository.findByUserId(userId);
    }

    public List<OwnerMember> findAll(){
        return ownerMemberRepository.findAll();
    }

    public void update(String userId, OwnerMember updateParam){
        Optional<OwnerMember> MemberOptional = findByUserId(userId);
        if(MemberOptional.isPresent()){
            OwnerMember member = MemberOptional.get();
            member.setPassword(updateParam.getPassword());
            ownerMemberRepository.save(member);
        }
    }

    public void deleteById(Long id){
        ownerMemberRepository.deleteById(id);
    }

}


