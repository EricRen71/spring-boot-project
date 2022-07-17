package net.springboot2.login.Config;

import net.springboot2.login.Interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    // 当前跨域请求最大有效时长1天
    private static final long MAX_AGE = 24 * 60 * 60;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 拦截所有的请求
                .allowedOrigins("*")    // 可跨域的域名,比如“http://www.baidu.com”等
                .allowedHeaders("*")    // 允许跨域的请求头，比如："Content-Type","X-Requested-With","accept,Origin","Access-Control-Request-Method","Access-Control-Request-Headers","token"
                .allowedMethods("GET", "PUT", "DELETE", "PUT", "HEAD", "OPTIONS")    // 允许跨域的方法，可以单独配置
                .maxAge(MAX_AGE)
                .allowCredentials(true);
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor(){
        return new AuthenticationInterceptor();
    }
}
