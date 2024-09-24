//package hello.hunfirst.controller;
//
//import hello.hunfirst.entity.Board;
//import hello.hunfirst.repository.BoardRepository;
//import hello.hunfirst.service.BoardService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/basic/boards")
//@RequiredArgsConstructor
//public class BasicBoardController {
//
//    private final BoardRepository boardRepository;
//    private final BoardService boardService;
//
//    // 전체 게시판 리스트 조회
//    @GetMapping
//    public String boards(Model model){
//        List<Board> boards = boardService.findAllBoards();  // Board 목록 가져오기
//        model.addAttribute("boards", boards);
//        return "basic/boards";  // 게시판 목록 페이지
//    }
//
//    // 특정 게시판 조회 (해당 게시판의 Recruit 리스트 포함)
//    @GetMapping("{boardId}")
//    public String board(@PathVariable long boardId, Model model){
//        Optional<Board> boardOptional = boardService.findBoardById(boardId);
//        if (boardOptional.isPresent()) {
//            Board board = boardOptional.get();
//            model.addAttribute("board", board);
//            model.addAttribute("recruits", board.getRecruits());  // Recruit 목록 포함
//        } else {
//            // 예외 처리나 오류 페이지로 리다이렉트
//            return "error/404";  // 적절한 오류 처리
//        }
//        return "basic/board";  // 특정 게시판 상세 페이지
//    }
//
//    // 게시판 추가 폼 이동
//    @GetMapping("/add")
//    public String addForm(){
//        return "basic/addForm";
//    }
//
//    // 게시판 추가 처리
//    @PostMapping("/add")
//    public String addBoard(Board board, RedirectAttributes redirectAttributes){
//        Board savedBoard = boardService.saveBoard(board);  // 게시판 저장
//        redirectAttributes.addAttribute("boardId", savedBoard.getId());
//        redirectAttributes.addAttribute("status", true);
//        return "redirect:/basic/boards/{boardId}";
//    }
//
//    // 게시판 수정 폼 이동
//    @GetMapping("/{boardId}/edit")
//    public String editForm(@PathVariable long boardId, Model model){
//        Optional<Board> board = boardService.findBoardById(boardId);
//        if (board.isPresent()) {
//            model.addAttribute("board", board.get());
//        } else {
//            return "error/404";  // 예외 처리
//        }
//        return "basic/editForm";  // 수정 페이지
//    }
//
//    // 게시판 수정 처리
//    @PostMapping("/{boardId}/edit")
//    public String edit(@PathVariable Long boardId, @RequestParam String boardTitle, @RequestParam String description) {
//        boardService.updateBoard(boardId, boardTitle, description);
//        return "redirect:/basic/boards/{boardId}";
//    }
//}
