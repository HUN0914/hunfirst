package hello.hunfirst.repository;

import hello.hunfirst.entity.OwnerMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerMemberRepository extends JpaRepository<OwnerMember, Long> {
    Optional<OwnerMember> findByUserId(String userId); // 추상 메서드

}
