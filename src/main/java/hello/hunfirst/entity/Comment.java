package hello.hunfirst.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COL_ID")
    private Long comId;

    private String userId;

    @ManyToOne
    @JoinColumn(name="Inquiry_ID")
    private Inquiry inquiry;

    @ManyToOne
    @JoinColumn(name="GENERAL_MEMBER_ID")
    private GeneralMember generalMember;




}
