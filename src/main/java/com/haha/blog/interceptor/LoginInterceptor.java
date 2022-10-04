package com.haha.blog.interceptor;

import com.haha.blog.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        String requestURI = request.getRequestURI();
        System.out.println("preHandle的请求路径时; " + requestURI);

        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");
        
        if(loginUser != null){
            return true;
        }
        
        request.setAttribute("msg","请先登录！");
        request.getRequestDispatcher("/login").forward(request,response);
        return false;
    }
}
