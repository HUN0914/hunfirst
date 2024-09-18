package hello.hunfirst.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="board")
public class  Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String boardTitle;
    private String content;


    @ManyToOne
    @JoinColumn(name = "OWNER_MEMBER_ID")  // 외래 키 지정
    private Owner_Member owner;  // 점주와의 다대일 관계

    public Board() {

    }
}
