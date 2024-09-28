package hello.hunfirst.repository;

import hello.hunfirst.entity.GeneralMember;
import hello.hunfirst.entity.Liked;
import hello.hunfirst.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikedRepository extends JpaRepository<Liked, Long> {

    //특정 사용자 특정 게시글 좋아요 했는지 확인
    Optional<Liked> findByGeneralMemberAndRecruit(GeneralMember generalMember, Recruit recruit);

    //게시글 대한 좋아요 갯수 반환
    Long countByRecruit(Recruit recruit);

    List<Liked> findByGeneralMember(GeneralMember generalMember);

}
