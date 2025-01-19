package com.nbmly.renting.housing.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "HousingBriefIntroductionDTO", description = "房源简介信息(1(有),0(无))")
public class HousingBriefIntroductionDTO implements Serializable {
    @ApiModelProperty("电视")
    private int television; // 电视
    @ApiModelProperty("冰箱")
    private int refrigerator; // 冰箱
    @ApiModelProperty("洗衣机")
    private int washing; // 洗衣机
    @ApiModelProperty("空调")
    private int airConditioner; // 空调
    @ApiModelProperty("热水器")
    private int heater; // 热水器
    @ApiModelProperty("床")
    private int bed; // 床
    @ApiModelProperty("暖气")
    private int heating; // 暖气
    @ApiModelProperty("宽带")
    private int broadband; // 宽带
    @ApiModelProperty("衣柜")
    private int wardrobe; // 衣柜
    @ApiModelProperty("天然气")
    private int gas; // 天然气

    public HousingBriefIntroductionDTO() {
    }

    public HousingBriefIntroductionDTO(int television, int refrigerator, int washing, int airConditioner, int heater,
            int bed, int heating, int broadband, int wardrobe, int gas) {
        this.television = television;
        this.refrigerator = refrigerator;
        this.washing = washing;
        this.airConditioner = airConditioner;
        this.heater = heater;
        this.bed = bed;
        this.heating = heating;
        this.broadband = broadband;
        this.wardrobe = wardrobe;
        this.gas = gas;
    }

    public int getTelevision() {
        return television;
    }

    public void setTelevision(int television) {
        this.television = television;
    }

    public int getRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(int refrigerator) {
        this.refrigerator = refrigerator;
    }

    public int getWashing() {
        return washing;
    }

    public void setWashing(int washing) {
        this.washing = washing;
    }

    public int getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(int airConditioner) {
        this.airConditioner = airConditioner;
    }

    public int getHeater() {
        return heater;
    }

    public void setHeater(int heater) {
        this.heater = heater;
    }

    public int getBed() {
        return bed;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    public int getHeating() {
        return heating;
    }

    public void setHeating(int heating) {
        this.heating = heating;
    }

    public int getBroadband() {
        return broadband;
    }

    public void setBroadband(int broadband) {
        this.broadband = broadband;
    }

    public int getWardrobe() {
        return wardrobe;
    }

    public void setWardrobe(int wardrobe) {
        this.wardrobe = wardrobe;
    }

    public int getGas() {
        return gas;
    }

    public void setGas(int gas) {
        this.gas = gas;
    }

    @Override
    public String toString() {
        return "HousingBriefIntroduction{" +
                ", television=" + television +
                ", refrigerator=" + refrigerator +
                ", washing=" + washing +
                ", airConditioner=" + airConditioner +
                ", heater=" + heater +
                ", bed=" + bed +
                ", heating=" + heating +
                ", broadband=" + broadband +
                ", wardrobe=" + wardrobe +
                ", gas=" + gas +
                '}';
    }
}
