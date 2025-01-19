package com.nbmly.renting.service;

import com.nbmly.renting.place.model.CityDTO;
import com.nbmly.renting.pojo.City;

import java.util.List;

public interface CityService {
    List<CityDTO> getCityList();
}
