package com.xiaye.rescuebackend.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册sa-token拦截器，使用StpUtil.checkLogin()登陆校验
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                .excludePathPatterns("/auth/login")//释放登陆接口
                .excludePathPatterns("/auth/register")//释放注册接口
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/**/doc.*",
                        "/**/swagger-ui.*",
                        "/**/swagger-resources",
                        "/**/webjars/**",
                        "/**/v3/api-docs/**");//释放api文档调试端口

    }
}
