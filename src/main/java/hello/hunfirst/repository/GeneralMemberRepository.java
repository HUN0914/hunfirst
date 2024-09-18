package hello.hunfirst.repository;

import hello.hunfirst.entity.GeneralMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneralMemberRepository extends JpaRepository<GeneralMember, Long> {
    Optional<GeneralMember> findByUserId(String userId); // 추상 메서드


}
