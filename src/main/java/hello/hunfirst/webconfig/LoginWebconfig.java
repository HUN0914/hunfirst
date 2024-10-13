import hello.hunfirst.Interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginWebconfig implements WebMvcConfigurer {

    private final LoginCheckInterceptor loginCheckInterceptor;

    public LoginWebconfig(LoginCheckInterceptor loginCheckInterceptor) {
        this.loginCheckInterceptor = loginCheckInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/",                   // 홈 화면
                        "/general/loginForm",   // 일반 회원 로그인 페이지
                        "/owner/loginForm",     // 사장님 회원 로그인 페이지
                        "/general/signupForm",  // 일반 회원 가입
                        "/owner/signupForm",    // 사장님 회원 가입
                        "/css/**", "/js/**", "/images/**", "/error"  // 정적 자원 제외
                );
    }
}
