package hello.hunfirst.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
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

    @OneToMany(mappedBy = "recruit") // Like 엔티티의 recruit 필드와 연결
    private List<Liked> likedList;

    @ManyToOne
    @JoinColumn(name="OWNER_MEMBER_ID")
    private OwnerMember ownerMember;


    public void updateRecruit(String title, String content, String startDate, String endDate, String favor) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        }
        if (content != null && !content.isEmpty()) {
            this.content = content;
        }
        if (startDate != null && !startDate.isEmpty()) {
            this.startDate = startDate;
        }
        if (endDate != null && !endDate.isEmpty()) {
            this.endDate = endDate;
        }
        if (favor != null && !favor.isEmpty()) {
            this.favor = favor;
        }
    }




}
