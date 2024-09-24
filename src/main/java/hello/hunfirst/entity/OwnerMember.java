package hello.hunfirst.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
@NotNull
public class OwnerMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OWNER_MEMBER_ID")
    private String userId;

    private String password;

    private String name;

    private Long ownerNum;

    @OneToMany
    @JoinColumn(name = "RECRUIT_ID")
    private List<Recruit> recruitList;

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }



}
