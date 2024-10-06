package hello.hunfirst.service;

import hello.hunfirst.entity.GeneralMember;
import hello.hunfirst.entity.Liked;
import hello.hunfirst.entity.Recruit;
import hello.hunfirst.repository.GeneralMemberRepository;
import hello.hunfirst.repository.LikedRepository;
import hello.hunfirst.repository.RecruitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikedService {

    private final LikedRepository likedRepository;
    private final RecruitRepository recruitRepository;
    private final GeneralMemberRepository generalMemberRepository;

    @Transactional
    public void likeRecruit(Long recruitId, String userId){
        Recruit recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 공고입니다."));
        GeneralMember generalMember = generalMemberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 회원입니다."));

        Liked liked = new Liked(generalMember, recruit);
        likedRepository.save(liked);
    }

    @Transactional
    public void unlikeRecruit(Long recruitId, String userId){
        Recruit recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 공고입니다."));
        GeneralMember generalMember = generalMemberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 회원입니다."));

        Liked liked = likedRepository.findByGeneralMemberAndRecruit(generalMember , recruit)
            .orElseThrow(() -> new IllegalArgumentException("좋아요가 존재하지 않습니다."));
        likedRepository.delete(liked);
    }

    public long getLikeCount(Long recruitId){
        return likedRepository.countByRecruit_RecruitId(recruitId);
    }


}
