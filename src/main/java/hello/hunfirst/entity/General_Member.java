package hello.hunfirst.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;  // 유효성 검사를 위한 올바른 @NotNull 패키지

import java.util.List;

@Entity
@Data
public class General_Member {

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
    public General_Member() {}

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    @OneToMany(mappedBy = "applicant")
    private List<ApplyHIstory> applications;  // 여러 지원 내역


}
