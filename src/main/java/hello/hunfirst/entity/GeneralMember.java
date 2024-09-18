package hello.hunfirst.entity;

import hello.hunfirst.inter.MemberInterface;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;  // 유효성 검사를 위한 올바른 @NotNull 패키지

import java.util.List;

@Entity
@Data
public class GeneralMember implements MemberInterface {

    @Id
    @GeneratedValue
    @Column(name = "GENERAL_MEMBER_ID")
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String userId;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "member")
    private List<Inquiry> inquiries;

    // 기본 생성자 (JPA에서 필수)
    public GeneralMember() {}

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    @OneToMany(mappedBy = "applicant")
    private List<ApplyHistory> applications;  // 여러 지원 내역


}
