package com.nbmly.renting.housing.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "HousingModeDTO", description = "出租方式")
public class HousingModeDTO implements Serializable {
    @ApiModelProperty("方式")
    private String mode;

    public HousingModeDTO() {
    }

    public HousingModeDTO(String mode) {
        this.mode = mode;
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
                ", mode='" + mode + '\'' +
                '}';
    }
}
