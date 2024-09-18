package hello.hunfirst.repository;

import hello.hunfirst.entity.General_Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface General_MemberRepository extends JpaRepository<General_Member, Long> {
    Optional<General_Member> findByUserId(String userId); // 추상 메서드


}
