package hello.hunfirst.repository;

import hello.hunfirst.entity.ApplyHIstory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyHistoryRepository extends JpaRepository<ApplyHIstory,Long > {
    List<ApplyHIstory> findByApplicantId(Long applicantId);  // 특정 지원자의 지원 내역
    List<ApplyHIstory> findByJobPostingId(Long jobPostingId);  // 특정 알바 공고에 대한 지원 내역
}
