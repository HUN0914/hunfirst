package hello.hunfirst.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String boardTitle;
    private String content;


    public Board(Long id, String boardTitle, String content) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.content = content;
    }

    public Board() {

    }
}
