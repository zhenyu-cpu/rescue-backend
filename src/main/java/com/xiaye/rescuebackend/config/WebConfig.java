package com.xiaye.rescuebackend.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 注册自定义的参数解析器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new MultiRequestBodyArgumentResolver());
        WebMvcConfigurer.super.addArgumentResolvers(argumentResolvers);
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        //设置日期格式
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(smt);
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        //设置中文编码格式
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.valueOf("application/json;charset=UTF-8"));
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
        converters.add(responseBodyConverter());
        converters.add(mappingJackson2HttpMessageConverter);
    }
}
