package hello.hunfirst.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Liked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKE_ID")
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "GENERAL_MEMBER_ID")
    private GeneralMember generalMember;

    @ManyToOne
    @JoinColumn(name = "RECRUIT_ID")
    private Recruit recruit;


}
