package com.nbmly.renting.service.imp;

import com.nbmly.renting.housing.model.HousingUserManagementDTO;
import com.nbmly.renting.mapper.HousingUserMapper;
import com.nbmly.renting.pojo.HousingUser;
import com.nbmly.renting.utils.HousingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HousingCommonService {

    @Autowired
    private HousingUserMapper housingUserMapper;

    /**
     * 根据id查询房屋信息
     * 
     * @param housingManagementId 房屋id
     * @return 房屋信息
     */
    public HousingUserManagementDTO getByIdHousing(long housingManagementId) {
        // 查询数据库
        HousingUser housingUser = housingUserMapper.getIdHousing(housingManagementId);
        if (housingUser == null) {
            return null;
        }
        // pojo转DTO
        List<HousingUser> tempList = new ArrayList<>();
        tempList.add(housingUser);
        List<HousingUserManagementDTO> housingUserManagementDTOS = HousingUtil.formatHousingUserManagementDTO(tempList);

        return housingUserManagementDTOS.get(0);
    }
}
