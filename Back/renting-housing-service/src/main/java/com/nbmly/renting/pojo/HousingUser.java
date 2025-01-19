package com.nbmly.renting.pojo;

import java.util.List;

// 出租人
public class HousingUser {
    private Long id; // 主键
    private Long accountId; // 账户Id
    private Long housingManagementId; // 房屋Id

    // 房屋集合
    private HousingManagement housingManagement;

    public HousingUser() {
    }

    public HousingUser(Long id, Long accountId, Long housingManagementId, HousingManagement housingManagement) {
        this.id = id;
        this.accountId = accountId;
        this.housingManagementId = housingManagementId;
        this.housingManagement = housingManagement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public HousingManagement getHousingManagement() {
        return housingManagement;
    }

    public void setHousingManagement(HousingManagement housingManagement) {
        this.housingManagement = housingManagement;
    }

    @Override
    public String toString() {
        return "HousingUser{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", housingManagementId=" + housingManagementId +
                ", housingManagement=" + housingManagement +
                '}';
    }
}
