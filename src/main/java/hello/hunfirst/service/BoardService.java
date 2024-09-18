package hello.hunfirst.service;

import hello.hunfirst.entity.Board;
import hello.hunfirst.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor  // final 필드에 대해 생성자 자동 생성
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public Board save(Board board) {
        return boardRepository.save(board);
    }

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    // 생성자 주입으로 필드 변경 없이 업데이트
    public void update(Long boardId, String newTitle, String newContent) {
        Optional<Board> boardOptional = findById(boardId);
        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();
            board.updateBoardTitle(newTitle);  // 제목 업데이트
            board.updateContent(newContent);  // 내용 업데이트
            boardRepository.save(board);  // 변경된 엔티티 저장
        }
    }

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
