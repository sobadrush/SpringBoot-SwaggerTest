package com.cathay.springbootswaggertest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author RogerLo
 * @date 2022/6/1
 *
 * ref. https://www.codingame.com/playgrounds/51075/rest-with-spring-boot-2/swagger
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // 配置content type
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
        new HashSet<>(Arrays.asList(
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        ));

    @Bean
    public Docket buildDocket() {
        // Docket - 一覽表；事項表；備忘錄
        return new Docket(DocumentationType.SWAGGER_2)
            // 提供 API 相關的資訊，不想設定可以跳過這個 apiInfo call
            .apiInfo(this.buildApiInfo())
            .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
            .produces(DEFAULT_PRODUCES_AND_CONSUMES)
            .select()
            // 設定 base package，只有在這個 package 底下的 REST API 才會加入 Swagger 中
            // 如果不想指定，也可以用 RequestHandlerSelectors.any() 代表所有的 REST API
            .apis(RequestHandlerSelectors.basePackage("com.cathay.springbootswaggertest.controller"))
            .paths(PathSelectors.any())
            .build();
    }

    /**
     * 作者的聯繫方式
     * */
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
            .title("ＲＯＧＥＲ’s RESTful API Swagger 文件")
            .contact(new Contact("ＲＯＧＥＲ ＬＯ", "https://github.com/sobadrush/SpringBoot-SwaggerTest", "sobadrush@icloud.com"))
            .version("9527")
            .build();
    }

}