package com.nbmly.renting.pojo;

import java.io.Serializable;

public class Account implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String cellPhone;
    private Integer state;
    private String domain;
    private String sex;
    private Long powerId;
    private String powerName;

    public Account() {
    }

    public Account(Long id, String username, String password, String cellPhone, Integer state, String domain,
            String sex, Long powerId, String powerName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
        this.state = state;
        this.domain = domain;
        this.sex = sex;
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
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", state=" + state +
                ", domain='" + domain + '\'' +
                ", sex='" + sex + '\'' +
                ", powerId=" + powerId +
                ", powerName='" + powerName + '\'' +
                '}';
    }
}
