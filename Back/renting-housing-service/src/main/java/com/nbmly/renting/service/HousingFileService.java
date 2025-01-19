package com.nbmly.renting.service;

import com.nbmly.renting.common.RestResponse;
import com.nbmly.renting.housing.model.HousingFileDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HousingFileService {
    HousingFileDTO upload(MultipartFile file);

    RestResponse<String> fileDel(HousingFileDTO housingFileDTO);
}
