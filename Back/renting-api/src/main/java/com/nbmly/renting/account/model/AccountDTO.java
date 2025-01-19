package com.nbmly.renting.account.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "AccountDTO", description = "用户信息")
public class AccountDTO implements Serializable {
    @ApiModelProperty("标识")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("手机号")
    private String cellPhone;
    @ApiModelProperty("状态:0冻结,1正常")
    private Integer state;
    @ApiModelProperty("范围(u:普通用户,r:管理员)")
    private String domain;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("权限")
    private List<String> powers;
    @ApiModelProperty("权限id")
    private Long powerId;
    @ApiModelProperty("权限名称")
    private String powerName;

    public AccountDTO() {
    }

    public AccountDTO(Long id, String username, String password, String cellPhone, Integer state, String domain,
            String sex, List<String> powers, Long powerId, String powerName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
        this.state = state;
        this.domain = domain;
        this.sex = sex;
        this.powers = powers;
        this.powerId = powerId;
        this.powerName = powerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<String> getPowers() {
        return powers;
    }

    public void setPowers(List<String> powers) {
        this.powers = powers;
    }

    public Long getPowerId() {
        return powerId;
    }

    public void setPowerId(Long powerId) {
        this.powerId = powerId;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", state=" + state +
                ", domain='" + domain + '\'' +
                ", sex='" + sex + '\'' +
                ", powers=" + powers +
                ", powerId=" + powerId +
                ", powerName='" + powerName + '\'' +
                '}';
    }
}
