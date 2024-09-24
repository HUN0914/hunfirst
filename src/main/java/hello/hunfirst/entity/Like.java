package hello.hunfirst.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @OneToMany(mappedBy = "GENERAL_MEMBER_ID")
    private List<GeneralMember> memberList;

    @OneToMany(mappedBy = "RECRUIT_ID")
    private List<Recruit> recruitList;

}
