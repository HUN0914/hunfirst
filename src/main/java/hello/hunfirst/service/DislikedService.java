package hello.hunfirst.service;

import hello.hunfirst.entity.DisLiked;
import hello.hunfirst.entity.GeneralMember;
import hello.hunfirst.entity.Recruit;
import hello.hunfirst.repository.DislikedRepository;
import hello.hunfirst.repository.GeneralMemberRepository;
import hello.hunfirst.repository.RecruitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DislikedService {

    private final DislikedRepository dislikedRepository;
    private final RecruitRepository recruitRepository;
    private final GeneralMemberRepository generalMemberRepository;

    @Transactional
    public void dislikeRecruit(Long recruitId, String userId){
        Recruit recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 공고입니다."));
        GeneralMember generalMember = generalMemberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 회원입니다."));

        DisLiked disLiked = new DisLiked(generalMember, recruit);
        dislikedRepository.save(disLiked);
    }

    public long getLikeCount(Long recruitId){
        return dislikedRepository.countByRecruit_RecruitId(recruitId);
    }


}
