package com.nbmly.renting.mapper;

import com.nbmly.renting.housing.model.HousingScreenDTO;
import com.nbmly.renting.pojo.HousingUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousingUserMapper {
    HousingUser getIdHousing(@Param("housingManagementId") long housingManagementId);

    List<HousingUser> getHousingUser(@Param("accountId") long accountId, @Param("page") int page,
            @Param("record") int record);

    List<HousingUser> getHousingUserScreen(@Param("hScreen") HousingScreenDTO housingScreenDTO, @Param("page") int page,
            @Param("record") int record);

    int saveHousing(@Param("accountId") long accountId, @Param("housingManagementId") long housingManagementId);

    int deleteHousingUser(@Param("housingManagementId") long housingManagementId);

    List<HousingUser> getLikeUserIdHousing(@Param("accountId") String accountId, @Param("page") int page,
            @Param("rows") int rows);

    int getLikeUserIdHousingCounts(@Param("accountId") String accountId);
}
