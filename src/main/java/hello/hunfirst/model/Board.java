package hello.hunfirst.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
