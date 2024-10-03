package hello.hunfirst.webconfig;

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
                .excludePathPatterns("/", "/login/signupForm", "/login/loginForm", "/login", "/css/**", "/js/**", "/images/**", "/error");
    }



}
