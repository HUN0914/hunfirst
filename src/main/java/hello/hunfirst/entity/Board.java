package hello.hunfirst.entity;

import jakarta.persistence.*;

@Entity
@Table(name="board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardTitle;
    private String content;

    @ManyToOne
    @JoinColumn(name = "OWNER_MEMBER_ID")  // 외래 키 지정
    private OwnerMember owner;  // 점주와의 다대일 관계

    // 생성자를 통해 필수 값 주입
    public Board(String boardTitle, String content, OwnerMember owner) {
        this.boardTitle = boardTitle;
        this.content = content;
        this.owner = owner;
    }

    // 기본 생성자 (JPA에서 필요)
    protected Board() {
    }

    // Getter만 제공 (Setter는 제거)
    public Long getId() {
        return id;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public String getContent() {
        return content;
    }

    public OwnerMember getOwner() {
        return owner;
    }

    // 필드를 변경할 수 있는 메서드 제공 (Setter 대신 명시적 메서드)
    public void updateBoardTitle(String newTitle) {
        this.boardTitle = newTitle;
    }

    public void updateContent(String newContent) {
        this.content = newContent;
    }
}
