package com.nbmly.renting.housing;

import com.nbmly.renting.common.RestResponse;
import com.nbmly.renting.housing.model.HousingScreenDTO;
import com.nbmly.renting.housing.model.HousingUserManagementDTO;

import java.util.List;

public interface HousingAPI {
    /**
     * 用户获取房屋信息
     * 
     * @param page 浏览页数
     * @return 房屋信息
     */
    RestResponse<List<HousingUserManagementDTO>> getHousing(int page, HousingScreenDTO housingScreenDTO);

    /**
     * 根据id查询房源信息
     * 
     * @param housingManagementId housingManagementId
     * @return
     */
    RestResponse<HousingUserManagementDTO> getIdHousing(long housingManagementId);

    /**
     * 根据用户id模糊查询房源信息
     * 
     * @param page      页码
     * @param rows      页数
     * @param accountId 用户id
     * @return
     */
    RestResponse<List<HousingUserManagementDTO>> getVagueUserIdHousing(int page, int rows, Long accountId);

    /**
     * 根据用户id模糊查询房源总页数
     * 
     * @param accountId 用户id
     * @return
     */
    RestResponse<Integer> getVagueUserIdHousingCounts(Long accountId);
}
