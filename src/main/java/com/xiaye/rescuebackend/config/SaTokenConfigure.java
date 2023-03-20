package com.xiaye.rescuebackend.config;

import cn.dev33.satoken.config.SaTokenConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * sa-token的配置文件
 */
@Configuration
public class SaTokenConfigure {
    @Bean
    @Primary
    public SaTokenConfig getSaTokenConfigPrimary() {
        SaTokenConfig config = new SaTokenConfig();
        // token名称，同时也是cookie名称
        config.setTokenName("Authorization");
        config.setTokenStyle("simple-uuid");
        config.setTokenPrefix("Bearer");
        //token有效期。单位s,默认30天
        config.setTimeout(30 * 24 * 60 * 60);
        //token临时有效期
        config.setActivityTimeout(30 * 60);
        //自动续签
        config.setAutoRenew(true);
        // 是否尝试从header里读取token
        config.setIsReadHeader(true);
        // 是否尝试从cookie里读取token
        config.setIsReadCookie(false);
        // 是否尝试从请求体里读取token
        config.setIsReadBody(false);
        // 是否输出操作日志
        config.setIsLog(true);
        return config;
    }
}
