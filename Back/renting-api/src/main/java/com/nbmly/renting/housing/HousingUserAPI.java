package com.nbmly.renting.housing;

import com.nbmly.renting.common.RestResponse;
import com.nbmly.renting.housing.model.HousingBriefIntroductionDTO;
import com.nbmly.renting.housing.model.HousingFileDTO;
import com.nbmly.renting.housing.model.HousingModeDTO;
import com.nbmly.renting.housing.model.HousingUserManagementDTO;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 用户类操作
 */
public interface HousingUserAPI {

    /**
     * 获取用户房源信息
     * 
     * @param jwtToken 请求头
     * @param page     浏览页数
     * @return 用户房源信息
     */
    RestResponse<List<HousingUserManagementDTO>> getUserManagement(String jwtToken, int page);

    /**
     * 保存用户房源信息
     * 
     * @param files                    图片列表
     * @param housingUserManagementDTO 用户房源信息
     * @return 处理结果
     */
    RestResponse<String> saveUserManagement(String jwtToken, MultipartFile files[], String housingUserManagementDTO);

    /**
     * 获取出租方式
     * 
     * @return 处理结果
     */
    RestResponse<List<HousingModeDTO>> getMode();

    /**
     * 修改用户房源信息
     * 
     * @param files                 图片列表
     * @param housingUserManagement 用户房源信息
     * @param newFileDTO            最新图片
     * @return 处理结果
     */
    RestResponse<String> reviseUserManagement(String jwtToken, MultipartFile[] files, String housingUserManagement,
            String newFileDTO);

    /**
     * 删除用户房源信息
     * 
     * @param housingManagementId 房源ID
     * @param accountId           用户ID
     * @param housingFileDTO      删除图片DTO对象
     * @return 处理结果
     */
    RestResponse<String> delUserManagement(String jwtToken, Long housingManagementId, Long accountId,
            HousingFileDTO housingFileDTO);

    /**
     * 获取房源简介
     * 
     * @param housingManagementId 房源ID
     * @return 房源简介信息
     */
    RestResponse<HousingBriefIntroductionDTO> getUserBriefIn(long housingManagementId);
}
