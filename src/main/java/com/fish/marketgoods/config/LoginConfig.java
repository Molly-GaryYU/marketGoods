package com.fish.marketgoods.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    public void LoginInterceptors(ViewControllerRegistry registry){
        //注册TestInterceptor拦截器
      /*  InterceptorRegistration registration= registry.addInterceptor();*/
        //作用和在控制类中使用return返回一个页面的作用一样
        //这个代码的意义应该就是为了防止控制类出现过多的这样的控制类没有代码只执行页面跳转
        registry.addViewController("/").setViewName("index");
    }
}
