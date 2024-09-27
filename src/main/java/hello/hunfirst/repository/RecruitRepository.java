package hello.hunfirst.repository;

import hello.hunfirst.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitRepository extends JpaRepository <Recruit, Long> {
}
