package com.cathay.springbootswaggertest.controller;

import com.cathay.springbootswaggertest.SpringBootSwaggerTestApplication;
import com.cathay.springbootswaggertest.model.CountryVO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author RogerLo
 * @date 2022/6/3
 */
@SpringBootTest(classes = SpringBootSwaggerTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${server.servlet.context-path}")
    private String contextPath; // should populate with /app-service

    @LocalServerPort // 使用隨機PORT → 需搭配 SpringBootTest.WebEnvironment.RANDOM_PORT 使用(ref. https://howtodoinjava.com/spring-boot2/testing/testresttemplate-post-example/)
    private int randomServerPort;

    @Test // 參考：https://mkyong.com/spring-boot/spring-boot-hello-world-example/
    @Disabled
    public void test001() throws Exception {
        final String endPointUrl = "http://localhost:"
                                    + randomServerPort
                                    + contextPath + "/CountryController/country/getAll";

        // 取得 ResponseEntity<集合形式>
        // ref. https://java.tutorialink.com/how-to-get-list-from-object-in-spring-resttemplate/
        ResponseEntity<Set<CountryVO>> responseEntity =
                restTemplate.exchange(
                endPointUrl,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Set<CountryVO>>() {}
        );
        System.err.println("responseEntity = " + responseEntity);
        System.err.println("responseEntity.getBody() = " + responseEntity.getBody());
        org.assertj.core.api.AssertionsForClassTypes.assertThat(
                responseEntity.getBody().size()).isGreaterThan(1);
    }

    @Test
    @Disabled
    public void test002() {
        final String endPointUrl = "http://localhost:"
                                + randomServerPort
                                + contextPath + "/CountryController/country";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Stream.of(MediaType.APPLICATION_JSON).collect(Collectors.toList()));

        CountryVO countryForInsert = CountryVO.builder().countryName("Gambia").build(); // 甘比亞
        HttpEntity<CountryVO> request = new HttpEntity<>(countryForInsert, headers);

        // 使用 postForEntity (ref. https://howtodoinjava.com/spring-boot2/testing/testresttemplate-post-example/)
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(endPointUrl, request, String.class);
        System.err.println("CountryControllerTest.test002 responseEntity = " + responseEntity);
    }
}