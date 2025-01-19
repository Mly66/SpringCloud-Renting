package com.nbmly.renting.pojo;

// 出租方式
public class HousingMode {
    private Long id;
    private String mode;

    public HousingMode() {
    }

    public HousingMode(Long id, String mode) {
        this.id = id;
        this.mode = mode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "HousingMode{" +
                "id=" + id +
                ", mode='" + mode + '\'' +
                '}';
    }
}
