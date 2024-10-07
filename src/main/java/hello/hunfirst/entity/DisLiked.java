package hello.hunfirst.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
이거 final로 만들면 only 한 멤버만 가능한것으로 됨
 */
@Entity
@Getter
@NoArgsConstructor
public class DisLiked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DISLIKE_ID")
    private  Long dislikeId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private GeneralMember generalMember;

    @ManyToOne
    @JoinColumn(name = "RECRUIT_ID")
    private Recruit recruit;

    //연관 관계 메서드
    public void assignGeneralMember(GeneralMember generalMember){
        this.generalMember = generalMember;
        //GeneralMember의 likedList에 현재 Liked 추가
        // -> 이 자체가 좋아요 하나만 넣을 수 있는거
        generalMember.getDisLikedList().add(this);
    }

    public void assignRecruit(Recruit recruit){
        this.recruit=recruit;
        recruit.getDisLikedList().add(this);
    }

    public DisLiked(GeneralMember generalMember, Recruit recruit){
        assignGeneralMember(generalMember);
        assignRecruit(recruit);
    }


}
