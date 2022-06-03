package com.cathay.springbootswaggertest.controller;

import com.cathay.springbootswaggertest.SpringBootSwaggerTestApplication;
import com.cathay.springbootswaggertest.model.CountryVO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Set;

/**
 * @author RogerLo
 * @date 2022/6/3
 */
@SpringBootTest(classes = SpringBootSwaggerTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test // 參考：https://mkyong.com/spring-boot/spring-boot-hello-world-example/
    @Disabled
    public void test001() throws Exception {
        // 取得 ResponseEntity<集合形式>
        // ref. https://java.tutorialink.com/how-to-get-list-from-object-in-spring-resttemplate/
        ResponseEntity<Set<CountryVO>> responseEntity =
                restTemplate.exchange(
                "http://localhost:8080/RogerSpringBoot/CountryController/country/getAll",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Set<CountryVO>>() {}
        );
        System.err.println("responseEntity = " + responseEntity);
        System.err.println("responseEntity.getBody() = " + responseEntity.getBody());
        org.assertj.core.api.AssertionsForClassTypes.assertThat(
                responseEntity.getBody().size()).isGreaterThan(1);
    }

}