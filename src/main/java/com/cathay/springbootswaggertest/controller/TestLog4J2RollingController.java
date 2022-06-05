package com.cathay.springbootswaggertest.controller;

import com.cathay.springbootswaggertest.annotation.RogerShowInSwagger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RogerLo
 * @date 2022/6/1
 */
@Log4j2
@Api(tags = "TestLog4J2RolleringController 相關 API")
@RequestMapping("/TestLog4J2RolleringController")
@RestController // @Controller + @ResponseBody
public class TestLog4J2RollingController {

    @RogerShowInSwagger
    @ApiOperation(value = "Swagger API: testLogRolling", notes = "測試Log4j2 RollingFile")
    @GetMapping(value = "/testLogRolling", produces = { MediaType.TEXT_PLAIN_VALUE })
    public String testLogRolling() {
        for (int i = 0; i < 1000; i++) {
            log.debug("... [debug] 測試log4j2 RollingFile ...");
            log.info("... [info] 測試log4j2 RollingFile ...");
        }
        return "I'm testLogRolling";
    }

}
