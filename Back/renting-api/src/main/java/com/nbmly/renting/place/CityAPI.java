package com.nbmly.renting.place;

import com.nbmly.renting.common.RestResponse;
import com.nbmly.renting.place.model.CityDTO;

import java.util.List;

public interface CityAPI {
    /**
     * 获取APP所有城市
     * 
     * @return 城市信息
     */
    RestResponse<List<CityDTO>> getCityList();
}
