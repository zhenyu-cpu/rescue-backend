package com.xiaye.rescuebackend.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册sa-token拦截器，使用StpUtil.checkLogin()登陆校验
        log.info("--------- 进入拦截器注册成功 ----------------------");
        registry.addInterceptor(new SaInterceptor(handle -> {
                    log.info("--------- 请求进入了拦截器，访问的 path 是：{} -----------", SaHolder.getRequest().getRequestPath());
                    StpUtil.checkLogin();
                }))
                .addPathPatterns("/**")
                .excludePathPatterns("/auth/login")//释放登陆接口
                .excludePathPatterns("/auth/register")//释放注册接口
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/utils/**")//释放工具接口
                .excludePathPatterns("/error")//错误处理接口
                .excludePathPatterns(
                        "/**/doc.*",
                        "/**/swagger-ui.**",
                        "/**/swagger-ui/**",
                        "/**/swagger-resources",
                        "/**/webjars/**",
                        "/**/v3/api-docs/**");//释放api文档调试端口
    }
}
