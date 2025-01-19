package com.nbmly.renting.place.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "CityDTO", description = "城市信息")
public class CityDTO {
    @ApiModelProperty("城市首字母")
    private String cityFirst;
    @ApiModelProperty("城市集合")
    private List<String> cityNames;

    public CityDTO() {
    }

    public CityDTO(String cityFirst, List<String> cityNames) {
        this.cityFirst = cityFirst;
        this.cityNames = cityNames;
    }

    public String getCityFirst() {
        return cityFirst;
    }

    public void setCityFirst(String cityFirst) {
        this.cityFirst = cityFirst;
    }

    public List<String> getCityNames() {
        return cityNames;
    }

    public void setCityNames(List<String> cityNames) {
        this.cityNames = cityNames;
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "cityFirst='" + cityFirst + '\'' +
                ", cityNames=" + cityNames +
                '}';
    }
}
