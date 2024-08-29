package hello.hunfirst.repository;

import hello.hunfirst.model.Board;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardRepository {

    private static final Map<Long, Board> store= new HashMap<>();
    private Long sequence = 0L;

    public Board save(Board board) {
        board.setId(++sequence);
        store.put(board.getId(), board);
        return board;
    }

    public Board findById(Long id){
        return store.get(id);
    }

    public void update(Long boardId, Board updateParam){
        Board findBoard = findById(boardId);
        findBoard.setBoardTitle(updateParam.getBoardTitle());
        findBoard.setContent(updateParam.getContent());

    }

    public List<Board> findAll() {
        return new ArrayList<>(store.values());
    }
}
