package com.cathay.springbootswaggertest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author RogerLo
 * @date 2022/6/3
 */
@Entity
@Table(name = "COUNTRY")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(description = "國家資料")
public class CountryVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "國家流水序號", required = true)
    @Column(name = "ID")
    private Long countryId;

    @ApiModelProperty(value = "國家英文名稱", required = true)
    @Column(name = "NAME", nullable = false)
    private String countryName;

    public CountryVO(String countryName) {
        this.countryName = countryName;
    }
}
