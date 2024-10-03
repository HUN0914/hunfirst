package hello.hunfirst.controller;

import hello.hunfirst.entity.GeneralMember;
import hello.hunfirst.entity.Recruit;
import hello.hunfirst.repository.RecruitRepository;
import hello.hunfirst.service.RecruitService;
import hello.hunfirst.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/recruit/recruits")
@Slf4j
public class RecruitController {

    private final RecruitService recruitService;

    // 전체 게시판 리스트 조회
    @GetMapping
    public String recruits(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

        if(session!=null) {
            GeneralMember loginedMember = (GeneralMember) session.getAttribute(SessionConst.LOGIN_MEMBER);
            model.addAttribute("name", loginedMember.getName());
            List<Recruit> boards = recruitService.findAll();  // Board 목록 가져오기
            model.addAttribute("boards", boards);
            return "recruit/recruits"; // 게시판 목록 페이지
        }
        else {
            return "redirect:/";
        }

    }

    @GetMapping("/add")
    public String addForm(Recruit recruit) {
        return "/recruit/addForm";
    }

    /*
    @Notnull, 이런거 유효성 검사하려면
    받는 객체 앞에 @Valid 어노테이션 (유효성 검사) 붙여줘야 함.
     */
    @PostMapping("/add")
    public ResponseEntity<String> addRecruit(@Valid @RequestBody Recruit recruit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("비어있는 항목이 있습니다.");
        }
        // 유효성 검사를 통과한 경우 저장
        recruitService.save(recruit);
        return ResponseEntity.ok("Recruit saved successfully");
    }

    @GetMapping("/{recruitId}")
    public String recruitDetails(@PathVariable Long recruitId, Model model) {
        // RecruitService를 이용하여 recruitId로 Recruit 엔티티를 조회
        Recruit recruit = recruitService.findById(recruitId);

        // 모델에 Recruit 객체를 추가하여 뷰에서 사용할 수 있도록 설정
        model.addAttribute("recruit", recruit);

        // 상세 정보 페이지로 이동 (예: recruit/recruitDetail.html)
        return "recruit/recruitDetail";
    }

    @DeleteMapping("/{recruitId}")
    public ResponseEntity<String> deleteRecruit(@PathVariable Long recruitId) {
        try {
            recruitService.deleteById(recruitId);
            return ResponseEntity.ok("성공적으로 삭제하였습니다.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제에 실패하였습니다");
        }
    }




}
