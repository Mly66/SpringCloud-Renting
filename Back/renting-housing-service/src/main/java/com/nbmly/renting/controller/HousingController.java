package com.nbmly.renting.controller;

import com.nbmly.renting.BusinessException;
import com.nbmly.renting.common.HousingErrorCode;
import com.nbmly.renting.common.RestResponse;
import com.nbmly.renting.housing.HousingAPI;
import com.nbmly.renting.housing.model.HousingScreenDTO;
import com.nbmly.renting.housing.model.HousingUserManagementDTO;
import com.nbmly.renting.service.HousingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "房屋服务的API", tags = { "房屋服务接口" })
public class HousingController implements HousingAPI {

    @Autowired
    private HousingService housingService;

    @PostMapping("/u/getHousing/{page}")
    @ApiOperation("用户分页查询房源信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "housingScreenDTO", value = "房源查询判断条件", required = true, dataType = "HousingScreenDTO", paramType = "body") })
    @Override
    public RestResponse<List<HousingUserManagementDTO>> getHousing(@PathVariable("page") int page,
            @RequestBody HousingScreenDTO housingScreenDTO) {
        if (housingScreenDTO == null) {
            throw new BusinessException(HousingErrorCode.E_1630401);
        }
        return RestResponse.success(housingService.getHousingInformation(page, housingScreenDTO));
    }

    @GetMapping("/u/getIdHousing/{housingManagementId}")
    @ApiOperation("根据id查询房源信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "housingManagementId", value = "房源id", required = true, dataType = "long"), })
    @Override
    public RestResponse<HousingUserManagementDTO> getIdHousing(
            @PathVariable("housingManagementId") long housingManagementId) {
        return RestResponse.success(housingService.getIdHousing(housingManagementId));
    }

    @PostMapping("/m/getVgUidHousing/{page}/{rows}")
    @ApiOperation("根据用户id模糊查询房源信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "accountId", value = "用户id(-1:否)", required = true, dataType = "Long"), })
    @Override
    public RestResponse<List<HousingUserManagementDTO>> getVagueUserIdHousing(
            @PathVariable("page") int page, @PathVariable("rows") int rows,
            @RequestParam("accountId") Long accountId) {
        List<HousingUserManagementDTO> housingDTO = housingService.getLikeUserIdHousing(accountId, page, rows);
        return RestResponse.success(housingDTO);
    }

    @GetMapping("/m/getVgUidHousingCounts/{accountId}")
    @ApiOperation("根据用户id模糊查询房源信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "用户id(-1:否)", required = true, dataType = "Long") })
    @Override
    public RestResponse<Integer> getVagueUserIdHousingCounts(@PathVariable("accountId") Long accountId) {
        Integer housingCounts = housingService.getLikeUserIdHousingCounts(accountId);
        return RestResponse.success(housingCounts);
    }
}