package com.cathay.springbootswaggertest.controller;

import com.cathay.springbootswaggertest.dao.CountryDAO;
import com.cathay.springbootswaggertest.model.CountryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author RogerLo
 * @date 2022/6/4
 *
 * RESTful API 參考：https://blog.csdn.net/weixin_38750084/article/details/102670177
 */
@Api(tags = "CountryController 相關 API")
@RequestMapping(value = "/CountryController")
@RestController
public class CountryController {

    @Autowired
    private CountryDAO countryDAO;

    @ApiOperation(value = "Swagger API: /country/getAll", notes = "查詢所有 Countries")
    @GetMapping(value = "/country/getAll", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Set<CountryVO> getAllCountries() {
        return countryDAO.findAll().stream().collect(Collectors.toSet());
    }

    @ApiOperation(value = "Swagger API: /country/getById", notes = "查詢 Country By Id")
    @GetMapping(value = "/country/getById/{cid}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public CountryVO getCountryById(@PathVariable("cid") Long countryIdL) {
        return countryDAO.findById(countryIdL)
                .orElseThrow(() -> new RuntimeException("無此 Country Id!"));
    }

}
