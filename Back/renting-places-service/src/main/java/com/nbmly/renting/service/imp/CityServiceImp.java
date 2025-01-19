package com.nbmly.renting.service.imp;

import com.nbmly.renting.mapper.CityMapper;
import com.nbmly.renting.place.model.CityDTO;
import com.nbmly.renting.pojo.City;
import com.nbmly.renting.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImp implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<CityDTO> getCityList() {
        List<CityDTO> cityDTOS = new ArrayList<>();
        List<City> cityList = cityMapper.getCityList();
        // 格式化数据
        for (City city : cityList) {
            boolean bg = false;
            int index = -1;
            for (CityDTO cityDTO : cityDTOS) {
                if (cityDTO.getCityFirst().equals(city.getCityFirst())) {
                    List<String> cityNames = cityDTO.getCityNames();
                    cityNames.add(city.getCityName());
                    cityDTO.setCityNames(cityNames);
                    bg = true;
                    break;
                }
            }
            if (!bg) {
                CityDTO cityDTO = new CityDTO();
                cityDTO.setCityFirst(city.getCityFirst());
                List<String> names = new ArrayList<>();
                names.add(city.getCityName());
                cityDTO.setCityNames(names);

                cityDTOS.add(cityDTO);
            }
        }

        // 返回数据
        return cityDTOS;
    }
}
