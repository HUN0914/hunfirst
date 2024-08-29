package hello.hunfirst.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {

    private Long id;
    private String boardTitle;
    private String content;


    public Board(Long id, String boardTitle, String content) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.content = content;
    }
}
