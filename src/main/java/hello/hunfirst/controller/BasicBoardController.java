package hello.hunfirst.controller;

import hello.hunfirst.entity.Board;
import hello.hunfirst.repository.BoardRepository;
import hello.hunfirst.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/basic/boards")
@RequiredArgsConstructor
public class BasicBoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @GetMapping
    public String boards(Model model){
        List<Board> boards= boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "basic/boards";
    }

    @GetMapping("{boardId}")
    public String board(@PathVariable long boardId, Model model){
        Optional<Board> boardOptional = boardService.findById(boardId);
        if (boardOptional.isPresent()) {
            model.addAttribute("board", boardOptional.get());
        } else {
            // 예외 처리나 오류 페이지로 리다이렉트
            return "error/404"; // 적절한 오류 처리
        }
        return "basic/board";
    }


    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String addBoard(Board board, RedirectAttributes redirectAttributes){
        Board savedBoard= boardRepository.save(board);
        redirectAttributes.addAttribute("boardId", savedBoard.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/boards/{boardId}";
        //    @GetMapping("{boardId}") 로 redirect 됨
    }

    //상품 수정
    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable long boardId, Model model){
        Optional<Board> board = boardService.findById(boardId);
        if (board.isPresent()) {
            model.addAttribute("board", board.get());
        } else {
            // 예외 처리나 오류 페이지로 리다이렉트
            return "error/404";// 적절한 오류 처리
        }
        return "basic/editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String edit(@PathVariable Long boardId, @RequestParam String boardTitle, @RequestParam String content) {
        boardService.update(boardId, boardTitle, content);
        return "redirect:/basic/boards/{boardId}";
    }

}
