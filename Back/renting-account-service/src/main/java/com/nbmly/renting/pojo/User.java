package com.nbmly.renting.pojo;

public class User {
    private Long userId;
    private Long accountId;
    private Power power;

    public User() {
    }

    public User(Long userId, Long accountId, Power power) {
        this.userId = userId;
        this.accountId = accountId;
        this.power = power;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", accountId=" + accountId +
                ", power=" + power +
                '}';
    }
}
