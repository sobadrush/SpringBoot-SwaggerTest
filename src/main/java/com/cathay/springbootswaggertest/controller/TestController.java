package com.cathay.springbootswaggertest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RogerLo
 * @date 2022/6/1
 */
@RequestMapping("/TestController")
@RestController // @Controller + @ResponseBody
public class TestController {

    // @ResponseBody
    @RequestMapping(value = "/sayHello", method = { RequestMethod.GET })
    public String sayHello() {
        return "Hello World! SpringBoot!!!";
    }

}
