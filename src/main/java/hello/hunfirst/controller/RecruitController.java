//package hello.hunfirst.controller;
//
//import hello.hunfirst.entity.Recruit;
//import hello.hunfirst.repository.RecruitRepository;
//import hello.hunfirst.service.RecruitService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Controller
//@RequestMapping("/basic/boards")
//public class RecruitController {
//
//    private final RecruitRepository recruitRepository;
//    private final RecruitService recruitService;
//
//    // 전체 게시판 리스트 조회
//    @GetMapping
//    public String recruits(Recruit model){
//        List<Recruit> boards = recruitService.findAll();  // Board 목록 가져오기
//        model.addAttribute("boards", boards);
//        return "basic/boards";  // 게시판 목록 페이지
//    }
//    @GetMapping()
//
//}
