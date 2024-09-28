package hello.hunfirst.controller;

import hello.hunfirst.entity.GeneralMember;
import hello.hunfirst.repository.GeneralMemberRepository;
import hello.hunfirst.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; // Lombok의 @Slf4j 어노테이션 import
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GeneralMemberController {

    private final GeneralMemberRepository memberRepository;

    @GetMapping("/")
    public String welcome() {
        return "login/loginForm";
    }

    @GetMapping("/login/signupForm")
    public String signupForm(Model model) {
        model.addAttribute("generalMember", new GeneralMember());
        return "login/signupForm";
    }

    @PostMapping("/login/signupForm")
    public String signup(@ModelAttribute GeneralMember generalMember, Model model) {

        log.info("User ID: {}", generalMember.getUserId());
        log.info("Name: {}", generalMember.getName());
        log.info("Password: {}", generalMember.getPassword());
        log.info("Age: {}", generalMember.getAge());
        log.info("Location: {}", generalMember.getLocation());

        memberRepository.save(generalMember);
        model.addAttribute("generalMember", generalMember);
        return "redirect:/";
    }

    @PostMapping("/login/loginForm")
    public String login(HttpServletRequest request, Model model) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        log.info("userId = {}", userId);
        log.info("password = {}", password);

        // 사용자 조회
        Optional<GeneralMember> checkId = memberRepository.findById(userId);

        if (checkId.isPresent() && checkId.get().getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_MEMBER, checkId.get());

            // 리다이렉트 구조 짜야함
            return "redirect:/recruit/recruits";


        } else {
            log.warn("Login failed.");

            model.addAttribute("loginError", "잘못된 접근입니다.");
            return "login/loginForm";
        }
    }
}
