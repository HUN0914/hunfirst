package hello.hunfirst.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
@NotNull
public class Recruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECRUIT_ID")
    private Long recruitId;

    private String title;

    private String startDate;

    private String endDate;

    private String content;

    private String favor;

    @OneToMany(mappedBy = "Like")
    private List<Like> likeList;

    @ManyToOne
    @JoinColumn(name="OWNER_MEMBER_ID")
    private OwnerMember ownerMember;




}
