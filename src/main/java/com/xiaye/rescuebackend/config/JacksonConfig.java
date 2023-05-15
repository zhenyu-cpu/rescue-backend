package com.xiaye.rescuebackend.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @className: JacksonConfig
 * @description: TODO 类描述
 * @author: zhenyu
 * @date: 2023/5/14
 **/
@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
            // 配置Jackson全局的日期格式
            builder.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            // 序列化null值为""
            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
            // 配置Jackson在反序列化时忽略不存在的属性
            builder.failOnUnknownProperties(true);
            // 配置Jackson在序列化时忽略值为null的属性
            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
            // 配置Jackson在序列化时忽略集合为空的属性
            builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
//            // 配置Jackson在序列化时将驼峰命名转为下划线命名
//            builder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
            // 配置Jackson在序列化时不包含为false的属性值
            builder.serializationInclusion(JsonInclude.Include.NON_DEFAULT);
            // 配置Jackson在序列化时不包含为空的集合属性值
            builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
        };
    }
}
