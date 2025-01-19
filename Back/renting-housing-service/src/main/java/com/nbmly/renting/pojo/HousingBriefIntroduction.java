package com.nbmly.renting.pojo;

// 房源简介
public class HousingBriefIntroduction {
    private Long id; // 主键
    private Long housingManagementId; // 房子Id
    private int television; // 电视
    private int refrigerator; // 冰箱
    private int washing; // 洗衣机
    private int airConditioner; // 空调
    private int heater; // 热水器
    private int bed; // 床
    private int heating; // 暖气
    private int broadband; // 宽带
    private int wardrobe; // 衣柜
    private int gas; // 天然气

    public HousingBriefIntroduction() {
    }

    public HousingBriefIntroduction(Long id, Long housingManagementId, int television, int refrigerator, int washing,
            int airConditioner, int heater, int bed, int heating, int broadband, int wardrobe, int gas) {
        this.id = id;
        this.housingManagementId = housingManagementId;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHousingManagementId() {
        return housingManagementId;
    }

    public void setHousingManagementId(Long housingManagementId) {
        this.housingManagementId = housingManagementId;
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
                "id=" + id +
                ", housingManagementId=" + housingManagementId +
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
