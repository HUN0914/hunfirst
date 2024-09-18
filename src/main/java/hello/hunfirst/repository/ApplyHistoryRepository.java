package hello.hunfirst.repository;

import hello.hunfirst.entity.ApplyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyHistoryRepository extends JpaRepository<ApplyHistory,Long > {
    List<ApplyHistory> findByApplicantId(Long applicantId);  // 특정 지원자의 지원 내역
    List<ApplyHistory> findByJobPostingId(Long jobPostingId);  // 특정 알바 공고에 대한 지원 내역
}
