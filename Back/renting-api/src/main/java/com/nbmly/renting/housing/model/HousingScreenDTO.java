package com.nbmly.renting.housing.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Arrays;

@ApiModel(value = "HousingScreenDTO", description = "房屋查询判断条件")
public class HousingScreenDTO implements Serializable {
    @ApiModelProperty("排序(ASC:升序,DESC:降序)")
    private String sort; // 排序(ASC:升序,DESC:降序)
    @ApiModelProperty("房屋标题")
    private String housingTitle; // 房屋标题
    @ApiModelProperty("出租方式")
    private Long[] modes; // 出租方式
    @ApiModelProperty("金额最低")
    private Double rentMin; // 金额最低
    @ApiModelProperty("金额最高")
    private Double rentMax; // 金额最高
    @ApiModelProperty("室")
    private Integer room; // 室
    @ApiModelProperty("厅")
    private Integer hall; // 厅
    @ApiModelProperty("地址")
    private String cityName; // 地址

    public HousingScreenDTO() {
    }

    public HousingScreenDTO(String sort, String housingTitle, Long[] modes, Double rentMin, Double rentMax,
            Integer room, Integer hall, String cityName) {
        this.sort = sort;
        this.housingTitle = housingTitle;
        this.modes = modes;
        this.rentMin = rentMin;
        this.rentMax = rentMax;
        this.room = room;
        this.hall = hall;
        this.cityName = cityName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getHousingTitle() {
        return housingTitle;
    }

    public void setHousingTitle(String housingTitle) {
        this.housingTitle = housingTitle;
    }

    public Long[] getModes() {
        return modes;
    }

    public void setModes(Long[] modes) {
        this.modes = modes;
    }

    public Double getRentMin() {
        return rentMin;
    }

    public void setRentMin(Double rentMin) {
        this.rentMin = rentMin;
    }

    public Double getRentMax() {
        return rentMax;
    }

    public void setRentMax(Double rentMax) {
        this.rentMax = rentMax;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getHall() {
        return hall;
    }

    public void setHall(Integer hall) {
        this.hall = hall;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "HousingScreenDTO{" +
                "sort='" + sort + '\'' +
                ", housingTitle='" + housingTitle + '\'' +
                ", modes=" + Arrays.toString(modes) +
                ", rentMin=" + rentMin +
                ", rentMax=" + rentMax +
                ", room=" + room +
                ", hall=" + hall +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
