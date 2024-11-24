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
@Table(name="Owner_Member")
@Data
public class OwnerMember {

    @Id
   @Column(name = "OWNER_ID", updatable = false, nullable = false, unique = true)
    private String ownerId;

    private String password;

    private String name;

    @OneToMany(mappedBy = "ownerMember")
    private List<Recruit> recruitList;

    public OwnerMember() {

    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }


    public void updatePassword(String password) {
        this.password=password;
    }

    public void updateName(String name) {
        this.name=name;
    }

    public void addRecruit(Recruit recruit) {
        this.recruitList.add(recruit);
    }


}
