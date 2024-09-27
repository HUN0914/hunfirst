package hello.hunfirst.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Entity
@NotNull
@Getter
public class OwnerMember {

    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public void updatePassword(String password) {
        this.password=password;
    }

    public void updateName(String name) {
        this.name=name;
    }

    public void updateownerNum(Long ownerNum) {
        this.ownerNum=ownerNum;
    }

}
