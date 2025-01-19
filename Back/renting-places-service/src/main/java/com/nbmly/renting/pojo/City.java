package com.nbmly.renting.pojo;

public class City {
    private Long id;
    private String cityFirst;
    private String cityName;

    public City() {
    }

    public City(Long id, String cityFirst, String cityName) {
        this.id = id;
        this.cityFirst = cityFirst;
        this.cityName = cityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityFirst() {
        return cityFirst;
    }

    public void setCityFirst(String cityFirst) {
        this.cityFirst = cityFirst;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityFirst='" + cityFirst + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
