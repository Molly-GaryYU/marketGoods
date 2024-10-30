package com.fish.marketgoods.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Interceptor 拦截器的意思 [ˌɪntəˈseptə(r)]
//加入Component注解让spring识别到
@Component
public class LoginHandler implements HandlerInterceptor {

    @Override
    //这个方法的意义是在执行Controller方法之前执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 1.需要判断 请求的接口是否为HandlerMethod（controller方法）
         * 2.判断token是否为空，null则为空
         * 3.token不为空登录验证
         * 4.认证成功 放行
         */
        return true;
    }
}
