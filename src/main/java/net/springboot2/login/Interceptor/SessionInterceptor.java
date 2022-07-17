package net.springboot2.login.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
            throws Exception {
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //普通路径放行
        if ("/login".equals(request.getRequestURI())) {
            return true;
        }
        //权限路径拦截
        Object object = request.getSession().getAttribute("username");
        if (object == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
