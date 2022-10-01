package com.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义一个拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    /**
     * 检测全局session对象中是否有uid数据，有则放行，没有则重定向到登录页面
     *  返回值为true 表示放行当前的请求 返回值为false 则表示拦截当前的请求
     *
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Object obj = request.getSession().getAttribute("uid");
        if(obj==null){
            //说明用户没有登录过系统 重定向到登录页面
            response.sendRedirect("/web/login.html");
            //结束后续的调用
            return false;
        }
        //请求放行
        return true;
    }
}
