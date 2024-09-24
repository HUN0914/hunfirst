package hello.hunfirst.service;

import hello.hunfirst.entity.Board;
import hello.hunfirst.entity.GeneralMember;
import hello.hunfirst.repository.BoardRepository;
import hello.hunfirst.repository.GeneralMemberRepository;
import hello.hunfirst.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final RecruitRepository recruitRepository;

    public List<Board> findAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> findBoardById(Long id) {
        return boardRepository.findById(id);
    }

    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    public void updateBoard(Long boardId, String content, String newDescription) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        board.setBoardTitle(content);
        board.setContent(content);
        boardRepository.save(board);
    }

}
