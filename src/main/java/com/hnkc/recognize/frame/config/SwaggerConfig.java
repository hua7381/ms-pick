package com.hnkc.recognize.frame.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 * 
 * @author zhangguihua
 * @date 2020/09/29
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                // 当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.hnkc.recognize.controller")).paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建api文档的详细信息函数
     * 
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("KC-微服务-文本要素提取").version("1.001")
                .description("维护: zhangguihua (hua7381@foxmail.com)").build();
    }

}
