package com.nbmly.renting.service;

import com.nbmly.renting.housing.model.HousingScreenDTO;
import com.nbmly.renting.housing.model.HousingUserManagementDTO;

import java.util.List;

public interface HousingService {
    List<HousingUserManagementDTO> getHousingInformation(int page, HousingScreenDTO housingScreenDTO);

    HousingUserManagementDTO getIdHousing(long housingManagementId);

    List<HousingUserManagementDTO> getLikeUserIdHousing(Long accountId, int page, int rows);

    Integer getLikeUserIdHousingCounts(Long accountId);
}
