package hello.hunfirst.service;

import hello.hunfirst.entity.GeneralMember;
import hello.hunfirst.entity.Recruit;
import hello.hunfirst.repository.GeneralMemberRepository;
import hello.hunfirst.repository.LikeRepository;
import hello.hunfirst.repository.RecruitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GeneralMemberService {

    private final GeneralMemberRepository generalRepository;
    private final LikeRepository likeRepository;
    private final RecruitRepository recruitRepository;

    public GeneralMember save(GeneralMember member){
        return generalRepository.save(member);
    }

    public Optional<GeneralMember> findById(String userId){
        return generalRepository.findById(userId);
    }

    public List<GeneralMember> findAll(){
        return generalRepository.findAll();
    }

    public void update(String userId, GeneralMember updateParam) {
        Optional<GeneralMember> memberOptional = findById(userId);
        /*
        -> JPADataRepository에서 findbyid로 찾아오는 것 (영속성 확정)
         */

        if (memberOptional.isPresent()) {
            // 2. 영속 상태의 엔티티를 수정
            GeneralMember updateMember = memberOptional.get();

            // 비즈니스 메서드를 이용해 필드를 수정
            updateMember.updatePassword(updateParam.getPassword());
            updateMember.updateName(updateParam.getName());
            updateMember.updateLocation(updateParam.getLocation());
            updateMember.updateAge(updateParam.getAge());

            // 3. 트랜잭션이 끝날 때 자동으로 데이터베이스에 반영 (별도의 save 호출 필요 없음)
        }
    }

    public void deleteById(String id){
        generalRepository.deleteById(id);
    }

    public void addLike(Long recruitId, String memberId){
        GeneralMember member = generalRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));
        Recruit recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물을 찾을 수 없습니다."));


     //   Optional<Like> existingLike = likeRepository.findByMemberAndRecruit(member, )
    }
}
