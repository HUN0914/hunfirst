package hello.hunfirst.webconfig;

import hello.hunfirst.Interceptor.LoginCheckInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class LoginWebconfig implements WebMvcConfigurer {

    private final LoginCheckInterceptor loginCheckInterceptor;

    public LoginWebconfig(LoginCheckInterceptor loginCheckInterceptor) {
        this.loginCheckInterceptor = loginCheckInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .order(1) // 인터셉터 실행 순서
                .addPathPatterns("/**") // 모든 경로에 인터셉터 적용
                .excludePathPatterns("/login", "/logout", "/signup", "/css/**", "/js/**", "/images/**");
        // 로그인, 회원가입, 정적 리소스는 예외 처리
    }
}
