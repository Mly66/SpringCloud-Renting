package com.nbmly.renting.utils;

import com.nbmly.renting.housing.model.HousingFileDTO;
import com.nbmly.renting.housing.model.HousingUserManagementDTO;
import com.nbmly.renting.pojo.HousingUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class HousingUtil {
    /**
     * 转换 DTO对象
     * 
     * @param housingUsers 用户信息pojo
     * @return 用户房源信息DTO
     */
    public static List<HousingUserManagementDTO> formatHousingUserManagementDTO(List<HousingUser> housingUsers) {
        List<HousingUserManagementDTO> housingUserManagementDTOS = new ArrayList<>();
        for (int i = 0; i < housingUsers.size(); i++) {
            HousingUserManagementDTO housingUserManagementDTO = new HousingUserManagementDTO();
            BeanUtils.copyProperties(housingUsers.get(i).getHousingManagement(), housingUserManagementDTO);
            housingUserManagementDTO.setHousingFileDTO(new HousingFileDTO(
                    housingUsers.get(i).getHousingManagement().getGroupName(),
                    housingUsers.get(i).getHousingManagement().getFileName()));
            housingUserManagementDTO.setHousingManagementId(housingUsers.get(i).getHousingManagementId());
            housingUserManagementDTO.setAccountId(housingUsers.get(i).getAccountId());
            housingUserManagementDTOS.add(housingUserManagementDTO);
        }
        return housingUserManagementDTOS;
    }
}
