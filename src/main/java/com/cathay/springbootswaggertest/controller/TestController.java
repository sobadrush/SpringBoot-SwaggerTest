package com.cathay.springbootswaggertest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RogerLo
 * @date 2022/6/1
 */
@Api(tags = "TestController 相關 API")
@RequestMapping("/TestController")
@RestController // @Controller + @ResponseBody
public class TestController {

    // @ResponseBody
    @ApiOperation(value = "Swagger API: sayHello", notes = "印出 Hello World 字樣")
    @ApiResponses({
        @ApiResponse(code = 401, message = "沒有權限"),
        @ApiResponse(code = 404, message = "找不到路徑")
    })
    @GetMapping(value = "/sayHello", produces = { MediaType.TEXT_PLAIN_VALUE })
    public String sayHello() {
        return "Hello World! SpringBoot!!!";
    }

}
