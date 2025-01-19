package com.nbmly.renting.account.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "AccountRegisterDTO", description = "用户注册信息")
public class AccountRegisterDTO implements Serializable {
    @ApiModelProperty("标识")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("手机号")
    private String cellPhone;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("性别")
    private String sex;

    public AccountRegisterDTO() {
    }

    public AccountRegisterDTO(Long id, String username, String cellPhone, String password, String sex) {
        this.id = id;
        this.username = username;
        this.cellPhone = cellPhone;
        this.password = password;
        this.sex = sex;
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

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "AccountRegisterDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
