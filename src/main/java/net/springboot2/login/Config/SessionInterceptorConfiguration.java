package net.springboot2.login.Config;

import net.springboot2.login.Interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SessionInterceptorConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry ){

        // 创建拦截器对象
        HandlerInterceptor interceptor = new SessionInterceptor();

        // 白名单
        /*List<String> patterns = new ArrayList<String>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/districts/**");
        patterns.add("/products/**");*/


        registry.addInterceptor(interceptor).addPathPatterns("/");
        //网站配置生成器：添加一个拦截器，拦截路径为整个项目
    }
}
