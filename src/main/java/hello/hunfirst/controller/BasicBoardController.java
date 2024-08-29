package hello.hunfirst.controller;

import hello.hunfirst.model.Board;
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
        Optional<Board> board = boardService.findById(boardId);
        model.addAttribute("board", board);
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
        model.addAttribute("board", board);
        return "basic/editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String edit(@PathVariable Long boardId, @ModelAttribute Board board){
        boardService.update(boardId, board);
        return "redirect:/basic/boards/{boardId}";
    }











}
