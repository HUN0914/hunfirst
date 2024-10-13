package hello.hunfirst.Interceptor;

import hello.hunfirst.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행: {}", requestURI);

        // 세션을 가져오고, 세션이 없으면 로그인 페이지로 리다이렉트
        HttpSession session = request.getSession(false);

        // 세션이 없거나, 세션에 저장된 로그인 정보가 없으면 로그인 페이지로 리다이렉트
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자 요청: {}", requestURI);

            // 로그인 후 원래 요청 경로로 돌아가도록 redirectURL 추가
            if (requestURI.startsWith("/general")) {
                // 일반 회원 요청인 경우 일반 회원 로그인 페이지로 리다이렉트 (redirectURL 추가)
                response.sendRedirect("/login?redirectURL=" + requestURI);
            } else if (requestURI.startsWith("/owner")) {
                // 사장님 회원 요청인 경우 사장님 로그인 페이지로 리다이렉트 (redirectURL 추가)
                response.sendRedirect("/login?redirectURL=" + requestURI);
            } else {
                // 그 외 요청인 경우 기본 경로로 리다이렉트 (필요 시 수정)
                response.sendRedirect("/login?redirectURL=" + requestURI);
            }
            return false; // 요청을 더 이상 처리하지 않음
        }

        log.info("인증된 사용자 요청: {}", requestURI);
        return true; // 인증된 사용자면 요청을 계속 처리
    }
}
