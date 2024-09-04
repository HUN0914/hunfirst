package hello.hunfirst.controller;

import hello.hunfirst.entity.Member;
import hello.hunfirst.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String welcome(){
        return "login";
    }

    @GetMapping("/signupForm")
    public String signupForm(Model model){
        model.addAttribute("member", new Member());
        return "signup/signupForm";
    }


    @PostMapping("/signupForm")
    public String signup(@ModelAttribute Member member, Model model){
        memberRepository.save(member);
        model.addAttribute("member", member);
        return "/login";
    }



}
