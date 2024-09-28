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
    @JoinColumn(name="INQUIRY_ID")
    private Inquiry inquiry;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private GeneralMember generalMember;

}
