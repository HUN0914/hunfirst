package hello.hunfirst.entity;

import jakarta.persistence.*;

@Entity
public class ApplyHIstory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPLICATION_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GENERAL_MEMBER_ID")  // 외래 키: 일반 회원
    private General_Member applicant;  // 지원자 (일반 회원)

    @ManyToOne
    @JoinColumn(name = "JOB_POSTING_ID")  // 외래 키: 알바 공고
    private Board Board;  // 지원한 알바 공고
}
