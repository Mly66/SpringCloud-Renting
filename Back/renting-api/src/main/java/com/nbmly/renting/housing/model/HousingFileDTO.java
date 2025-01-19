package com.nbmly.renting.housing.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "HousingFileDTO", description = "图片信息")
public class HousingFileDTO implements Serializable {
    @ApiModelProperty("图片组名")
    private String groupName;
    @ApiModelProperty("图片名称")
    private String fileName;

    public HousingFileDTO() {
    }

    public HousingFileDTO(String groupName, String fileName) {
        this.groupName = groupName;
        this.fileName = fileName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "HousingFileDTO{" +
                "groupName='" + groupName + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
