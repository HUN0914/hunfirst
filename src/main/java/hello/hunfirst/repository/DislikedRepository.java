package hello.hunfirst.repository;

import hello.hunfirst.entity.DisLiked;
import hello.hunfirst.entity.GeneralMember;
import hello.hunfirst.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DislikedRepository extends JpaRepository<DisLiked, Long> {

    //특정 사용자 특정 게시글 싫어요 했는지 확인
    Optional<DisLiked> findByGeneralMemberAndRecruit(GeneralMember generalMember, Recruit recruit);
    //게시글 대한 좋아요 갯수 반환
    Long countByRecruit(Recruit recruit);
    List<DisLiked> findByGeneralMember(GeneralMember generalMember);

    /*
    연관관계 매핑으로 인해
    이 쿼리는 liked 숫자를 갖고오는 쿼리로 작성된다.
     */
    long countByRecruit_RecruitId(Long recruitId);
}
