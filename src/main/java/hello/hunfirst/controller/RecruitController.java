package hello.hunfirst.controller;

import hello.hunfirst.dto.RecruitRequest;
import hello.hunfirst.entity.GeneralMember;
import hello.hunfirst.entity.OwnerMember;
import hello.hunfirst.entity.Recruit;
import hello.hunfirst.repository.RecruitRepository;
import hello.hunfirst.service.RecruitService;
import hello.hunfirst.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
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

    private final RecruitRepository recruitRepository;
    private Recruit recruit;
    private final RecruitService recruitService;
    private GeneralMember generalMember;

    // 전체 게시판 리스트 조회
    @GetMapping
    public String recruits(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

        if(session!=null) {
            Object loginMember= session.getAttribute(SessionConst.LOGIN_MEMBER);

            if(loginMember instanceof GeneralMember generalMember) {
                model.addAttribute("name", generalMember.getName());
            } else if (loginMember instanceof OwnerMember ownerMember) {
                model.addAttribute("name", ownerMember.getName());
            }
            else {
                return "redirect:/";
            }
            List<Recruit> recruits = recruitService.findAll();
            model.addAttribute("recruits", recruits);
            return "recruit/recruits";
        }

        return "redirect:/";
    }

    @GetMapping("/add")
    public String addForm(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session != null) {
            Object loginedMember = session.getAttribute(SessionConst.LOGIN_MEMBER);
            if (loginedMember instanceof OwnerMember) {
                return "/recruit/addForm";
            }
        }
        model.addAttribute("errorMessage", "게시글 등록 권한이 없습니다.");
        return "redirect:/recruit/recruits"; // 기존 게시판 페이지로 리다이렉트
    }
    /*
    @Notnull, 이런거 유효성 검사하려면
    받는 객체 앞에 @Valid 어노테이션 (유효성 검사) 붙여줘야 함.
     */
    @PostMapping("/add")
    public ResponseEntity<String> addRecruit(HttpServletRequest httpServletRequest,
                                             @Valid @RequestBody Recruit recruitRequest,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("비어있는 항목이 있습니다.");
        }

        HttpSession session = httpServletRequest.getSession();
        Object loginMember = session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (loginMember instanceof OwnerMember ownerMember) {
            recruitService.createRecruit(recruitRequest, ownerMember);
            return ResponseEntity.ok("공고가 성공적으로 등록되었습니다.");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("게시글 작성 권한이 없습니다.");
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
    public ResponseEntity<String> deleteRecruit(HttpServletRequest request, @PathVariable Long recruitId) {
        try {
            if (!checkUserId(request, recruitId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("삭제 권한이 없습니다.");
            }            recruitService.deleteById(recruitId);
            return ResponseEntity.ok("성공적으로 삭제하였습니다.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제에 실패하였습니다");
        }

    }

    @GetMapping("/edit/{recruitId}")
    public String editForm(HttpServletRequest request, @PathVariable Long recruitId, Model model) {
        log.debug("editForm 메서드 호출됨. recruitId: {}", recruitId);

        Recruit recruit = recruitService.findById(recruitId);
        model.addAttribute("recruit", recruit);
        return "recruit/edit"; // templates/recruit/edit.html로 이동
    }
    @PostMapping("/edit")
    public String editRecruit(@ModelAttribute @Valid RecruitRequest recruitRequest,
                              BindingResult bindingResult, Model model) {
        log.debug("Received RecruitRequest: {}", recruitRequest);

        if (bindingResult.hasErrors()) {
            log.error("Binding errors: {}", bindingResult.getAllErrors());
            model.addAttribute("recruit", recruitRequest);
            return "recruit/edit"; // 에러 발생 시 수정 페이지로 다시 이동
        }

        recruitService.updateRecruit(recruitRequest);

        return "redirect:/recruit/recruits/" + recruitRequest.getRecruitId();
    }

    @GetMapping("/search")
    public String searchByTitle(@RequestParam("title") String title, Model model) {
        List<Recruit> searchResults = recruitService.searchTitleContaining(title);

        model.addAttribute("recruits", searchResults);
        model.addAttribute("searchKeyword", title);

        return "recruit/recruits";
    }

    public boolean checkUserId(HttpServletRequest request, Long recruitId) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            log.warn("세션이 없습니다.");
            return false;
        }

        Object loginMember = session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember instanceof OwnerMember ownerMember) {
            Recruit recruit = recruitService.findById(recruitId);

            if (recruit.getOwnerMember() == null) {
                log.warn("게시글에 연결된 OwnerMember가 없습니다. recruitId={}", recruitId);
                return false;
            }

            boolean isOwner = ownerMember.getOwnerId().equals(recruit.getOwnerMember().getOwnerId());
            log.debug("로그인 사용자와 게시글 작성자 일치 여부: {}", isOwner);
            return isOwner;
        }

        log.warn("로그인한 사용자가 OwnerMember가 아닙니다.");
        return false;
    }

}
