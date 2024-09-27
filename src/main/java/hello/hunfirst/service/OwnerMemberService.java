package hello.hunfirst.service;

import hello.hunfirst.entity.GeneralMember;
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

    public Optional<OwnerMember> findById(String userId){
        return ownerMemberRepository.findById(userId);
    }

    public List<OwnerMember> findAll(){
        return ownerMemberRepository.findAll();
    }

    public void update(String userId, OwnerMember updateParam){
        Optional<OwnerMember> memberOptional = findById(userId);
        /*
        -> JPADataRepository에서 findbyid로 찾아오는 것 (영속성 확정)
         */

        if (memberOptional.isPresent()) {
            // 2. 영속 상태의 엔티티를 수정
            OwnerMember updateMember = memberOptional.get();

            // 비즈니스 메서드를 이용해 필드를 수정
            updateMember.updatePassword(updateParam.getPassword());
            updateMember.updateName(updateParam.getName());
            updateMember.updateownerNum(updateParam.getOwnerNum());
            // 3. 트랜잭션이 끝날 때 자동으로 데이터베이스에 반영 (별도의 save 호출 필요 없음)
        }
    }
    public void deleteById(String id){
        ownerMemberRepository.deleteById(id);
    }

}


