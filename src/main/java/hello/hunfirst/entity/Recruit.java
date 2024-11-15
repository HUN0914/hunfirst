package hello.hunfirst.entity;

import hello.hunfirst.RecruitTime;
import hello.hunfirst.entity.Liked;
import hello.hunfirst.entity.OwnerMember;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Recruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECRUIT_ID")
    private Long recruitId;

    @NotBlank(message = "제목은 빈칸이 될 수 없습니다")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "시작일은 빈칸이 될 수 없습니다")
    @Column(nullable = false)
    private String startDate;

    @NotBlank(message = "종료일은 빈칸이 될 수 없습니다")
    @Column(nullable = false)
    private String endDate;

    @NotBlank(message = "내용은 빈칸이 될 수 없습니다")
    @Column(nullable = false)
    private String content;

    @NotBlank(message = "선호도는 빈칸이 될 수 없습니다")
    @Column(nullable = false)
    private String favor;

    @OneToMany(mappedBy = "recruit") // Like 엔티티의 recruit 필드와 연결
    private List<Liked> likedList;

    @OneToMany(mappedBy = "recruit") // dislike 엔티티의 recruit 필드와 연결
    private List<DisLiked> disLikedList;

    @ManyToOne
    @JoinColumn(name="OWNER_MEMBER_ID")
    private OwnerMember ownerMember;

    private RecruitTime recruitTime;

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
