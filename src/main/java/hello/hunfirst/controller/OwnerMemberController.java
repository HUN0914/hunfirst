package hello.hunfirst.controller;

import hello.hunfirst.entity.OwnerMember;
import hello.hunfirst.repository.OwnerMemberRepository;
import hello.hunfirst.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OwnerMemberController {

    private final OwnerMemberRepository ownerMemberRepository;

    @GetMapping("/owner/signupForm")
    public String signupForm(Model model) {
        model.addAttribute("ownerMember", new OwnerMember());
        return "owner/signupForm"; // 템플릿 파일 이름
    }

    @GetMapping("/owner/loginForm")
    public String loginForm(Model model){
        model.addAttribute("ownerMember", new OwnerMember());
        return "owner/loginForm";
    }

    @PostMapping("/owner/signupForm")
    public String signup(@ModelAttribute OwnerMember ownerMember, Model model) {
        log.info("===== signup 메서드 호출됨 =====");
        log.info("User ID: {}", ownerMember.getOwnerId());
        log.info("Name: {}", ownerMember.getName());
        log.info("Password: {}", ownerMember.getPassword());

        if (ownerMember.getOwnerId() == null) {
            log.error("Owner ID가 null입니다.");
            model.addAttribute("signupError", "Owner ID가 입력되지 않았습니다.");
            return "owner/signupForm";
        }

        Optional<OwnerMember> existOwnerMember = ownerMemberRepository.findById(ownerMember.getOwnerId());
        if (existOwnerMember.isPresent()) {
            model.addAttribute("signupError", "Owner ID가 이미 존재합니다.");
            return "owner/signupForm";
        }

        ownerMemberRepository.save(ownerMember);
        return "redirect:/owner/loginForm";
    }

    @PostMapping("/owner/loginForm")
    public String login(HttpServletRequest request, Model model) {
        String ownerId = request.getParameter("ownerId");
        String password = request.getParameter("password");

        log.info("userId = {}", ownerId);
        log.info("password = {}", password);

        // 사용자 조회
        Optional<OwnerMember> checkId = ownerMemberRepository.findById(ownerId);

        if (checkId.isPresent() && checkId.get().getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_MEMBER, checkId.get());

            log.info("로그인 성공: {}", session.getAttribute(SessionConst.LOGIN_MEMBER)); // 세션에 저장된 로그인 정보 확인

            return "redirect:/recruit/recruits";


        } else {
            log.warn("Login failed.");

            model.addAttribute("loginError", "잘못된 접근입니다.");
            return "owner/loginForm";
        }
    }

}
