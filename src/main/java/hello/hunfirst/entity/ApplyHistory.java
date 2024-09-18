package hello.hunfirst.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class ApplyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPLICATION_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GENERAL_MEMBER_ID")  // 외래 키: 일반 회원
    private GeneralMember applicant;  // 지원자 (일반 회원)

    @ManyToOne
    @JoinColumn(name = "JOB_POSTING_ID")  // 외래 키: 알바 공고
    private Board Board;  // 지원한 알바 공고

    private LocalDateTime applicationDate;  // 지원 날짜
    private String status;  // 지원 상태 (예: "지원 중", "채용 완료")

}
