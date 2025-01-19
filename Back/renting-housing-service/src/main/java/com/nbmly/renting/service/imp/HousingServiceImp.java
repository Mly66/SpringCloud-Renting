package com.nbmly.renting.service.imp;

import com.nbmly.renting.common.HousingCommon;
import com.nbmly.renting.housing.model.HousingScreenDTO;
import com.nbmly.renting.housing.model.HousingUserManagementDTO;
import com.nbmly.renting.mapper.HousingManagementMapper;
import com.nbmly.renting.mapper.HousingUserMapper;
import com.nbmly.renting.pojo.HousingUser;
import com.nbmly.renting.service.HousingService;
import com.nbmly.renting.service.HousingUserService;
import com.nbmly.renting.utils.HousingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HousingServiceImp implements HousingService {
    @Autowired
    private HousingCommonService housingCommonService;
    @Autowired
    private HousingUserMapper housingUserMapper;

    @Override
    public List<HousingUserManagementDTO> getHousingInformation(int page, HousingScreenDTO housingScreenDTO) {
        // 分页处理
        page = (page - 1) * HousingCommon.RECORD;
        // 查询数据库
        List<HousingUser> housingUser = housingUserMapper.getHousingUserScreen(housingScreenDTO, page,
                HousingCommon.RECORD);
        // pojo转DTO
        List<HousingUserManagementDTO> housingUserManagementDTOS = HousingUtil
                .formatHousingUserManagementDTO(housingUser);

        return housingUserManagementDTOS;
    }

    @Cacheable(cacheNames = { "housing" }, key = "#housingManagementId", unless = "#result == null")
    @Override
    public HousingUserManagementDTO getIdHousing(long housingManagementId) {
        HousingUserManagementDTO housingUserManagementDTO = housingCommonService.getByIdHousing(housingManagementId);
        return housingUserManagementDTO;
    }

    @Override
    public List<HousingUserManagementDTO> getLikeUserIdHousing(Long accountId, int page, int rows) {
        // 分页处理
        page = (page - 1) * rows;
        // 查询数据库
        List<HousingUser> housingUser = housingUserMapper.getLikeUserIdHousing(accountId.toString(), page, rows);
        // pojo转DTO
        List<HousingUserManagementDTO> housingUserManagementDTOS = HousingUtil
                .formatHousingUserManagementDTO(housingUser);
        return housingUserManagementDTOS;
    }

    @Override
    public Integer getLikeUserIdHousingCounts(Long accountId) {
        int idHousingCounts = housingUserMapper.getLikeUserIdHousingCounts(accountId.toString());
        return idHousingCounts;
    }
}
