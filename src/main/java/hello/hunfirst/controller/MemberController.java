package hello.hunfirst.controller;

import hello.hunfirst.entity.General_Member;
import hello.hunfirst.repository.General_MemberRepository;
import hello.hunfirst.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final General_MemberRepository memberRepository;

    @GetMapping("/")
    public String welcome(){
        return "login/loginForm";
    }

    @GetMapping("/login/signupForm")
    public String signupForm(Model model){
        model.addAttribute("member", new General_Member());
        return "login/signupForm";
    }


    @PostMapping("/login/signupForm")
    public String signup(@ModelAttribute General_Member member, Model model){
        memberRepository.save(member);
        model.addAttribute("member", member);
        return "redirect:/";
    }

    /*
    로직을 생갹해야함
    파라미터로 userId와 password를 받고
    기존 db에 있는 userid, passoword인지 확인
    그리고 있을 경우 board(index.html)로 이동
    아니면 로그인 화면으로 이동

     */
    @PostMapping("/login/loginForm")
    public String login(HttpServletRequest request, Model model){
        String userId=request.getParameter("userId");
        String password=request.getParameter("password");

        System.out.println("userId = " + userId);
        System.out.println("password = " + password);

        //사용자 조회
        Optional<General_Member> checkId = memberRepository.findByUserId(userId);

        if(checkId.isPresent()&&checkId.get().getPassword().equals(password))
        {
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_MEMBER,checkId.get());

            //리다이렉트 구조 짜야함
            return "redirect:/basic/boards";

        }
        else{
            System.out.println("Login failed.");

            model.addAttribute("loginError", "잘못된 접근입니다." );
            return "login/loginForm";
        }
    }




}
