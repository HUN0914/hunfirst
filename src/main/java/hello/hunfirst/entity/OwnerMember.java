package hello.hunfirst.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class OwnerMember {

    @Id
    @GeneratedValue
    @Column(name = "OWNER_MEMBER_ID")
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

    public OwnerMember() {
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    @OneToMany(mappedBy = "owner")
    private List<Board> BoardList;
}
