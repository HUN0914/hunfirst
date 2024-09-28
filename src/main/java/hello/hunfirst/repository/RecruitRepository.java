package hello.hunfirst.repository;

import hello.hunfirst.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitRepository extends JpaRepository <Recruit, Long> {
    List<Recruit> findByTitle(String title);
}