package com.nbmly.renting.housing.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "HousingUserManagementDTO", description = "账户房源信息")
public class HousingUserManagementDTO implements Serializable {
    @ApiModelProperty("账户ID")
    private Long accountId; // 账户ID
    @ApiModelProperty("房源ID")
    private Long housingManagementId; // 房源ID
    @ApiModelProperty("城市")
    private String cityName; // 城市
    @ApiModelProperty("出租状态")
    private Integer state; // 出租状态
    @ApiModelProperty("详情地址")
    private String address; // 详情地址
    @ApiModelProperty("房屋标题")
    private String housingTitle; // 房屋标题
    @ApiModelProperty("租金")
    private Double rent; // 租金
    @ApiModelProperty("出租方式")
    private String mode; // 出租方式
    @ApiModelProperty("室")
    private Integer room; // 室
    @ApiModelProperty("厅")
    private Integer hall; // 厅
    @ApiModelProperty("浏览人数")
    private Long see; // 浏览人数
    @ApiModelProperty("房源简介")
    private HousingBriefIntroductionDTO housingBriefIntroductionDTO; // 房源简介

    @ApiModelProperty("图片信息")
    private HousingFileDTO housingFileDTO; // 图片形象

    public HousingUserManagementDTO() {
    }

    public HousingUserManagementDTO(Long accountId, Long housingManagementId, String cityName, Integer state,
            String address, String housingTitle, Double rent, String mode, Integer room, Integer hall, Long see,
            HousingBriefIntroductionDTO housingBriefIntroductionDTO, HousingFileDTO housingFileDTO) {
        this.accountId = accountId;
        this.housingManagementId = housingManagementId;
        this.cityName = cityName;
        this.state = state;
        this.address = address;
        this.housingTitle = housingTitle;
        this.rent = rent;
        this.mode = mode;
        this.room = room;
        this.hall = hall;
        this.see = see;
        this.housingBriefIntroductionDTO = housingBriefIntroductionDTO;
        this.housingFileDTO = housingFileDTO;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getHousingManagementId() {
        return housingManagementId;
    }

    public void setHousingManagementId(Long housingManagementId) {
        this.housingManagementId = housingManagementId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHousingTitle() {
        return housingTitle;
    }

    public void setHousingTitle(String housingTitle) {
        this.housingTitle = housingTitle;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
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

    public Long getSee() {
        return see;
    }

    public void setSee(Long see) {
        this.see = see;
    }

    public HousingBriefIntroductionDTO getHousingBriefIntroductionDTO() {
        return housingBriefIntroductionDTO;
    }

    public void setHousingBriefIntroductionDTO(HousingBriefIntroductionDTO housingBriefIntroductionDTO) {
        this.housingBriefIntroductionDTO = housingBriefIntroductionDTO;
    }

    public HousingFileDTO getHousingFileDTO() {
        return housingFileDTO;
    }

    public void setHousingFileDTO(HousingFileDTO housingFileDTO) {
        this.housingFileDTO = housingFileDTO;
    }

    @Override
    public String toString() {
        return "HousingUserManagementDTO{" +
                "accountId=" + accountId +
                ", housingManagementId=" + housingManagementId +
                ", cityName='" + cityName + '\'' +
                ", state=" + state +
                ", address='" + address + '\'' +
                ", housingTitle='" + housingTitle + '\'' +
                ", rent=" + rent +
                ", mode='" + mode + '\'' +
                ", room=" + room +
                ", hall=" + hall +
                ", see=" + see +
                ", housingBriefIntroductionDTO=" + housingBriefIntroductionDTO +
                ", housingFileDTO=" + housingFileDTO +
                '}';
    }
}
