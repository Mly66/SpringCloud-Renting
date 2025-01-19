package com.nbmly.renting.mapper;

import com.nbmly.renting.pojo.HousingBriefIntroduction;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousingBriefIntroductionMapper {
    int saveHousingBriefIntroduction(HousingBriefIntroduction housingBriefIntroduction);

    HousingBriefIntroduction getHousingBriefIntroduction(@Param("id") Long id);

    int updateHousingBriefIntroduction(HousingBriefIntroduction housingBriefIntroduction);

    int deleteHousingBriefIntroduction(@Param("housingManagementId") long housingManagementId);
}
