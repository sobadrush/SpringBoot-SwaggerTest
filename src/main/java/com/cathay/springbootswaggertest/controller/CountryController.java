package com.cathay.springbootswaggertest.controller;

import com.cathay.springbootswaggertest.dao.CountryDAO;
import com.cathay.springbootswaggertest.model.CountryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @ApiOperation(value = "Swagger API: 查所有國家", notes = "查詢所有 Countries")
    @GetMapping(value = "/country/getAll", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Set<CountryVO> getAllCountries() {
        return countryDAO.findAll().stream().collect(Collectors.toSet());
    }

    @ApiOperation(value = "Swagger API: 根據id查國家", notes = "查詢 Country By Id")
    @GetMapping(value = "/country/getById/{cid}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public CountryVO getCountryById(
            @ApiParam(required = true, value = "國家流水號")
            @PathVariable("cid") Long countryIdL) {
        return countryDAO.findById(countryIdL)
                .orElseThrow(() -> new RuntimeException("無此 Country Id!"));
    }

    @ApiOperation(value = "Swagger API: 根據名稱查國家", notes = "查詢 Country By Name")
    @GetMapping(value = "/country/getByName/{cName}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public CountryVO getCountryByName(
            @ApiParam(required = true, value = "國家名稱")
            @PathVariable("cName") String countryName) {
        return countryDAO.queryByNameParam(countryName);
    }

    @ApiOperation(value = "Swagger API: 新增國家", notes = "新增國家")
    @PostMapping(value = "/country", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Create Country Success")
    public Long addOneCounty(
            @ApiParam(required = true, value = "欲新增的國家物件資料")
            @RequestBody CountryVO countryVO) {
        // ▲ 可使用 return ResponseEntity<> 回應 Http 狀態碼，或是使用 @ResponseStatus (推薦！有 reason 較明確)
        CountryVO newCountryVO = countryDAO.saveAndFlush(countryVO);
        // return new ResponseEntity<>(newCountryVO.getCountryId(), HttpStatus.CREATED);
        return newCountryVO.getCountryId();
    }

}
