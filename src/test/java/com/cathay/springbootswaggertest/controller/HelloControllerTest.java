package com.cathay.springbootswaggertest.controller;

import com.cathay.springbootswaggertest.SpringBootSwaggerTestApplication;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
/**
 * @author RogerLo
 * @date 2022/6/1
 */
@SpringBootTest(classes = SpringBootSwaggerTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test // 參考：https://mkyong.com/spring-boot/spring-boot-hello-world-example/
    @Disabled
    public void test001() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/TestController/sayHello", String.class);
        System.out.println("response = " + response);
        System.out.println("response.getBody() = " + response.getBody());
        org.assertj.core.api.AssertionsForClassTypes.assertThat(
                response.getBody()).isEqualTo("Hello World! SpringBoot!!!");
    }

}