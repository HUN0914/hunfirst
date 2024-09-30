package hello.hunfirst.controller;

import hello.hunfirst.entity.Recruit;
import hello.hunfirst.repository.RecruitRepository;
import hello.hunfirst.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/recruit/recruits")
public class RecruitController {

    private final RecruitRepository recruitRepository;
    private final RecruitService recruitService;

    // 전체 게시판 리스트 조회
    @GetMapping
    public String recruits(Model model){
        List<Recruit> boards = recruitService.findAll();  // Board 목록 가져오기
        model.addAttribute("boards", boards);
        return "recruit/recruits"; // 게시판 목록 페이지
    }

    @GetMapping("/add")
    public String addForm(Recruit recruit){
        return "/recruit/addForm";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRecruit(@RequestBody Recruit recruit) {
        recruitService.save(recruit);
        return ResponseEntity.ok("Recruit saved successfully");
    }

    // @GetMapping("/{recruitId}")



}
