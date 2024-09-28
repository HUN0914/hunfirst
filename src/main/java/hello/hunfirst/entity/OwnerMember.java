package hello.hunfirst.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@NotNull
@Getter
public class OwnerMember {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "OWNER_ID", updatable = false, nullable = false)
    private String ownerId;

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
