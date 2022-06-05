package com.cathay.springbootswaggertest.controller;

import com.cathay.springbootswaggertest.annotation.RogerShowInSwagger;
import com.cathay.springbootswaggertest.dao.CountryDAO;
import com.cathay.springbootswaggertest.model.CountryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RogerShowInSwagger
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

    @ApiOperation(value = "Swagger API: 刪除國家", notes = "刪除國家")
    @DeleteMapping(value = "/country/delete/{cId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Long> deleteOneCountry(
            @ApiParam(required = true, value = "欲刪除的國家流水號")
            @PathVariable("cId") Long countryId) {
        countryDAO.deleteById(countryId);
        return new ResponseEntity<>(countryId, HttpStatus.OK); // ▲ 可使用 return ResponseEntity<> 回應 Http 狀態碼，或是使用 @ResponseStatus (推薦！有 reason 較明確)
    }

    @ApiOperation(value = "Swagger API: 修改國家PUT", notes = "修改國家PUT")
    @PutMapping(value = "/country/modify/{cId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(code = HttpStatus.OK, reason = "Modify Country Success")
    public CountryVO modifyCountry(
            @ApiParam(required = true, value = "欲更新的國家資料")
            @RequestBody CountryVO newCountryVO,
            @ApiParam(required = true, value = "欲更新的國家流水號")
            @PathVariable("cId") Long countryId) {
        return countryDAO.findById(countryId)
            .map(existedCountryVO -> {
                // 有找到 → 更新物件
                existedCountryVO.setCountryName(newCountryVO.getCountryName());
                existedCountryVO.setMemorialDay(newCountryVO.getMemorialDay());
                CountryVO updatedCountryVO = countryDAO.saveAndFlush(existedCountryVO);
                return updatedCountryVO;
            }).orElseGet(() -> {
                // 沒找到 → 新增物件
                newCountryVO.setCountryId(countryId);
                CountryVO insertedCountryVO = countryDAO.saveAndFlush(newCountryVO);
                return insertedCountryVO;
            });
    }

    @ApiOperation(value = "Swagger API: 修改國家PATCH", notes = "修改國家PATCH")
    @PatchMapping("/country/modify/{cId}")
    public ResponseEntity<CountryVO> modifyCountryPartially(
            @ApiParam(required = true, value = "欲更新的國家流水號")
            @PathVariable(value = "cId") Long countryId,
            @ApiParam(required = true, value = "欲更新的國家資料MODEL")
            @RequestBody CountryVO countryForUpdate) throws ResourceNotFoundException {

        CountryVO existCountry = countryDAO.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found on :: " + countryId));

        if (StringUtils.isNotBlank(countryForUpdate.getCountryName())) {
            existCountry.setCountryName(countryForUpdate.getCountryName());
        }
        if (countryForUpdate.getMemorialDay() != null) {
            existCountry.setMemorialDay(countryForUpdate.getMemorialDay());
        }
        CountryVO updatedCountry = countryDAO.saveAndFlush(existCountry);
        return ResponseEntity.ok(updatedCountry);
    }

}
