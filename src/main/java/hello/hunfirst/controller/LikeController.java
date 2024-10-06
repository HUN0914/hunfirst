package hello.hunfirst.controller;

import hello.hunfirst.entity.GeneralMember;
import hello.hunfirst.service.LikedService;
import hello.hunfirst.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikedService likedService;

    @PostMapping("/{recruitId}")
    public ResponseEntity<String> likeRecruit(@PathVariable Long recruitId, HttpServletRequest request) {
        // 세션에서 사용자 정보 가져오기
        GeneralMember generalMember = (GeneralMember) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);

        if (generalMember == null) {
            return ResponseEntity.status(401).body("인증되지 않은 사용자입니다.");
        }

        String userId = generalMember.getUserId();
        likedService.likeRecruit(recruitId, userId);

        return ResponseEntity.ok("좋아요 성공");
    }

    @DeleteMapping("/{recruitId}")
    public ResponseEntity<String> unlikeRecruit(@PathVariable Long recruitId, HttpServletRequest request) {
        // 세션에서 사용자 정보 가져오기
        GeneralMember generalMember = (GeneralMember) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);

        if (generalMember == null) {
            return ResponseEntity.status(401).body("인증되지 않은 사용자입니다.");
        }

        String userId = generalMember.getUserId();
        likedService.unlikeRecruit(recruitId, userId);

        return ResponseEntity.ok("좋아요 취소 성공");
    }

    @GetMapping("{recruitId}/count")
    public ResponseEntity<Long> getLikeCount(@PathVariable Long recruitId){
        long likeCount= likedService.getLikeCount(recruitId);
        return ResponseEntity.ok(likeCount);
    }
}
