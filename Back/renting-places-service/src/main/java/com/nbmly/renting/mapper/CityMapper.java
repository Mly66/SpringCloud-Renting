package com.nbmly.renting.mapper;

import com.nbmly.renting.pojo.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityMapper {
    List<City> getCityList();
}
