package com.nbmly.renting.mapper;

import com.nbmly.renting.pojo.HousingManagement;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousingManagementMapper {
    int saveHousingManagement(HousingManagement housingManagement);

    int updateHousingManagement(HousingManagement housingManagement);

    int deleteHousingManagement(@Param("id") long id);
}
