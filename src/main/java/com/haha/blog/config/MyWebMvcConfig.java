package com.haha.blog.config;

import com.haha.blog.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**") //admin下所有请求都被拦截包括静态资源
                .excludePathPatterns("/admin","/admin/login","/login","/css/**","/lib/**","/images/**","/showdown-master/**");
    }
    
    
    
    
}
