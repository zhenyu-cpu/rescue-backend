package com.xiaye.rescuebackend.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * knife4的配置文件
 */
@Configuration
@EnableKnife4j
@EnableSwagger2
public class Knife4jConfig {
    public static final String SWAGGER_TITLE = "消防救援应用项目接口";
    public static final String SWAGGER_API_VERSION = "0.0.1";
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .apiInfo(apiInfo())
                .groupName("xiaye")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xiaye.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(SWAGGER_TITLE)
                .version(SWAGGER_API_VERSION)
                .build();
    }
}
