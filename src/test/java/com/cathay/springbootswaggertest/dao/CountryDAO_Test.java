package com.cathay.springbootswaggertest.dao;

import com.cathay.springbootswaggertest.SpringBootSwaggerTestApplication;
import com.cathay.springbootswaggertest.model.CountryVO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author RogerLo
 * @date 2022/6/3
 */
@SpringBootTest(classes = SpringBootSwaggerTestApplication.class)
@Transactional
public class CountryDAO_Test {

    @Autowired
    private CountryDAO countryDAO;

    @Test
    @Disabled
    void test001() {
        countryDAO.findAll().stream().forEach(System.err::println);
    }

    @Test
    @Disabled
    void test002() {
        Optional<CountryVO> countryOpt = countryDAO.findById(3L);
        System.out.println("countryOpt.orElseGet(() -> {}) = " +
                countryOpt.orElseGet(() ->
                        CountryVO.builder()
                                .countryId(99L)
                                .countryName("印度")
                                .build()));
    }

    @Test
    @Disabled
    void test003() {
        System.err.println("countryDAO.queryByName(\"Brazil\") = " + countryDAO.queryByName("Brazil"));
    }

    @Test
    @Disabled
    void test004() {
        System.err.println("countryDAO.queryByNameParam(\"Italy\") = " + countryDAO.queryByNameParam("Italy"));
    }

    @Test
    @Disabled
    @Rollback(value = false)
    void test005() {
        countryDAO.save(CountryVO.builder().countryName("Taiwan").build());
    }

    @Test
    @Disabled
    @Rollback(value = false)
    void test006() {
        countryDAO.deleteCountryVOByCountryName("Taiwan");
    }

    @Test
    @Disabled
    @Rollback(value = false)
    void test007() {
        CountryVO countryVO = countryDAO.findById(1L).get();
        countryVO.setCountryName("America");
        countryDAO.saveAndFlush(countryVO);
        countryDAO.findAll().stream().forEach(System.err::println);
    }

}
