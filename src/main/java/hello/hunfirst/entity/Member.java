package hello.hunfirst.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;  // 유효성 검사를 위한 올바른 @NotNull 패키지

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
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

    // 기본 생성자 (JPA에서 필수)
    public Member() {
    }
}
