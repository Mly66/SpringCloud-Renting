package com.nbmly.renting.controller;

import com.nbmly.renting.common.RestResponse;
import com.nbmly.renting.place.CityAPI;
import com.nbmly.renting.place.model.CityDTO;
import com.nbmly.renting.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "城市服务的API", tags = { "城市服务接口" })
public class CityController implements CityAPI {

    @Autowired
    private CityService cityService;

    @ApiOperation("获取城市信息")
    @GetMapping("/u/getCityList")
    @Override
    public RestResponse<List<CityDTO>> getCityList() {
        return RestResponse.success(cityService.getCityList());
    }
}
