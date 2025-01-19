package com.nbmly.renting.pojo;

public class Power {
    private Long powerId;
    private String powerName;
    private String powerScope;

    public Power() {
    }

    public Power(Long powerId, String powerName, String powerScope) {
        this.powerId = powerId;
        this.powerName = powerName;
        this.powerScope = powerScope;
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

    public String getPowerScope() {
        return powerScope;
    }

    public void setPowerScope(String powerScope) {
        this.powerScope = powerScope;
    }

    @Override
    public String toString() {
        return "Power{" +
                "powerId=" + powerId +
                ", powerName='" + powerName + '\'' +
                ", powerScope='" + powerScope + '\'' +
                '}';
    }
}
