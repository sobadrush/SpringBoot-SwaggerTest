package com.cathay.springbootswaggertest.dao;

import com.cathay.springbootswaggertest.model.CountryVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author RogerLo
 * @date 2022/6/3
 */
@Repository
public interface CountryDAO extends JpaRepository<CountryVO, Long> {

    // 自定義SQL查詢(Native SQL)
    @Query(value = "SELECT * FROM COUNTRY WHERE NAME = ?1", nativeQuery = true)
    CountryVO queryByName(String countryName);

    // 自定義SQL查詢(Native SQL) + 具名變數
    @Query(value = "SELECT * FROM COUNTRY WHERE NAME = :c_name", nativeQuery = true)
    CountryVO queryByNameParam(@Param("c_name") String countryName);

    void deleteCountryVOByCountryName(String countryName);
}
