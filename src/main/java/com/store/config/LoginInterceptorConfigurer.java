package com.store.config;

import com.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

//这个类来完成处理器，拦截器的注册
@Configuration//去加载当前的拦截器并进行注册
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建一个拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();
        //配置白名单 存放在一个list集合中
        List<String> patterns = new ArrayList<>();
        //以下界面可以在未登录的情况下访问
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
        patterns.add("/products/**");



        //拦截器的注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")//表示要拦截的url  黑名单
                .excludePathPatterns(patterns); //除了这些不拦截  白名单


    }
}
