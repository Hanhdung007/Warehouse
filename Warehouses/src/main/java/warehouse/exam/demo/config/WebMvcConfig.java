package warehouse.exam.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import warehouse.exam.demo.interceptor.SessionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/import/**")
                .addPathPatterns("/error/**")
                .addPathPatterns("/itemaster/**")
                .addPathPatterns("/itemdata/**")
                .addPathPatterns("/location/**")
                .addPathPatterns("/warehouse/**")
                .addPathPatterns("/qc/**")
                .excludePathPatterns("/auth/login", "/auth/logout");
    }
}
