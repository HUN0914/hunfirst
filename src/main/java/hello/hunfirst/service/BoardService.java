package hello.hunfirst.service;

import hello.hunfirst.model.Board;
import hello.hunfirst.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board save(Board board){
        return boardRepository.save(board);
    }
    public Optional<Board> findById(Long id){
        return boardRepository.findById(id);
    }

    public List<Board> findAll(){
        return boardRepository.findAll();}

    public void update(Long boardId, Board updateParam){
        Optional<Board> boardOptional = findById(boardId);
        if(boardOptional.isPresent()){
            Board board = boardOptional.get();
            board.setBoardTitle(updateParam.getBoardTitle());
            board.setContent(updateParam.getContent());
            boardRepository.save(board);
        }
    }

    public void deleteById(Long id){
        boardRepository.deleteById(id);
    }

}
