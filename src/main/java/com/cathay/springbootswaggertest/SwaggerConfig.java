package com.cathay.springbootswaggertest;

import com.cathay.springbootswaggertest.annotation.RogerShowInSwagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.RequestHandler;
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
import java.util.function.Predicate;

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
    public Docket docketA() {
        return this.buildDocket("Group_A",
                RequestHandlerSelectors.basePackage("com.cathay.springbootswaggertest.controller"));
    }

    @Bean
    public Docket docketB() {
        return this.buildDocket("Group_B",
                RequestHandlerSelectors.withMethodAnnotation(GetMapping.class));
    }

    @Bean
    public Docket docketC() {
        return this.buildDocket("Group_C",
                RequestHandlerSelectors.withMethodAnnotation(RogerShowInSwagger.class));
    }

    /**
     * 建立 Swagger Docket 物件
     * */
    private Docket buildDocket(String groupName, Predicate<RequestHandler> selector) {
        // Docket - 一覽表；事項表；備忘錄
        return new Docket(DocumentationType.SWAGGER_2)
            // 提供 API 相關的資訊，不想設定可以跳過這個 apiInfo call
            .apiInfo(this.buildApiInfo())
            .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
            .produces(DEFAULT_PRODUCES_AND_CONSUMES)
            .groupName(groupName) // 分組名稱
            .select()
            // 設定 base package，只有在這個 package 底下的 REST API 才會加入 Swagger 中
            // 如果不想指定，也可以用 RequestHandlerSelectors.any() 代表所有的 REST API
            .apis(selector)
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