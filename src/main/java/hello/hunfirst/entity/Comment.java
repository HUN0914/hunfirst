package hello.hunfirst.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COL_ID")
    private Long comId;

    private String content;

    @ManyToOne
    @JoinColumn(name="Inquiry_ID")
    private Inquiry inquiry;

    @ManyToOne
    @JoinColumn(name="GENERAL_MEMBER_ID")
    private GeneralMember generalMember;

}
