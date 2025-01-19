package com.nbmly.renting.service;

import com.nbmly.renting.housing.model.HousingBriefIntroductionDTO;
import com.nbmly.renting.housing.model.HousingFileDTO;
import com.nbmly.renting.housing.model.HousingModeDTO;
import com.nbmly.renting.housing.model.HousingUserManagementDTO;
import com.nbmly.renting.pojo.HousingManagement;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HousingUserService {
    List<HousingUserManagementDTO> getHousingUserInformation(long accountId, int page);

    String saveHousingUserManagement(MultipartFile[] files, HousingUserManagementDTO housingUserManagementDTO);

    HousingUserManagementDTO updateHousingUserManagement(MultipartFile[] files,
            HousingUserManagementDTO housingUserManagementDTO, HousingFileDTO housingFileDTO);

    List<HousingModeDTO> getHousingMode();

    HousingBriefIntroductionDTO getHousingBriefIntroduction(long housingManagementId);

    void deleteHousingUserManagement(Long housingManagementId, HousingFileDTO housingFileDTO);

}
