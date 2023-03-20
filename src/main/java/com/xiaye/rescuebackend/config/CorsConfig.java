package com.xiaye.rescuebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                //所有项目解耦都支持跨域
                .addMapping("/**")
                //所有地址均可以访问
                .allowedOrigins("*")
                //允许所有请求方法
                .allowedMethods("*")
                //跨域允许时间
                .maxAge(3600);
    }
}
