package com.nbmly.renting.controller;

import com.alibaba.fastjson.JSON;
import com.nbmly.renting.BusinessException;
import com.nbmly.renting.account.model.AccountDTO;
import com.nbmly.renting.common.HousingErrorCode;
import com.nbmly.renting.housing.HousingUserAPI;
import com.nbmly.renting.housing.model.*;
import com.nbmly.renting.service.HousingUserService;
import com.nbmly.renting.common.RestResponse;
import com.nbmly.renting.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Api(value = "用户房屋服务的API", tags = { "用户房屋服务接口" })
public class HousingUserController implements HousingUserAPI {
    @Autowired
    private HousingUserService housingUserService;

    @Override
    @ApiOperation("获取用户房源信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jwtToken", value = "请求头:Authorization", required = true, dataType = "String"),
            @ApiImplicitParam(name = "page", value = "浏览页数", required = true, dataType = "int") })
    @GetMapping("/bus/getUserManagement/{page}")
    public RestResponse<List<HousingUserManagementDTO>> getUserManagement(
            @RequestHeader("Authorization") String jwtToken, @PathVariable("page") int page) {
        // 获取当前用户ID
        String user_name = TokenUtil.chekaToken(TokenUtil.workToken(jwtToken));
        AccountDTO accountDTO = JSON.parseObject(user_name, AccountDTO.class);
        return RestResponse.success(housingUserService.getHousingUserInformation(accountDTO.getId(), page));
    }

    @Override
    @ApiOperation("保存账户房源信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "文件信息", required = true, dataType = "MultipartFile[]", paramType = "body"),
            @ApiImplicitParam(name = "housingManagementDTO", value = "账户房源信息", required = true, dataType = "HousingManagementDTO", paramType = "body") })
    @PostMapping("/bus/saveUserManagement")
    public RestResponse<String> saveUserManagement(
            @RequestHeader("Authorization") String jwtToken,
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("housingManagementDTO") String housingManagementDTO) {
        HousingUserManagementDTO housingUserManagementDTO = JSON.parseObject(housingManagementDTO,
                HousingUserManagementDTO.class);
        if (housingUserManagementDTO == null) {
            throw new BusinessException(HousingErrorCode.E_1630101);
        }
        // 获取当前用户
        String user_name = TokenUtil.chekaToken(TokenUtil.workToken(jwtToken));
        AccountDTO accountDTO = JSON.parseObject(user_name, AccountDTO.class);
        housingUserManagementDTO.setAccountId(accountDTO.getId());
        return RestResponse.success(housingUserService.saveHousingUserManagement(files, housingUserManagementDTO));
    }

    @Override
    @ApiOperation("修改账户房源信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "文件信息", required = true, dataType = "MultipartFile[]", paramType = "body"),
            @ApiImplicitParam(name = "housingManagementDTO", value = "账户房源信息", required = true, dataType = "HousingManagementDTO", paramType = "body"),
            @ApiImplicitParam(name = "newFileDTO", value = "新的图片", required = true, dataType = "String") })
    @PostMapping("/bus/reviseUserManagement")
    public RestResponse<String> reviseUserManagement(
            @RequestHeader("Authorization") String jwtToken,
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("housingManagementDTO") String housingUserManagement,
            @RequestParam("newFiles") String newFileDTO) {
        // 获取当前用户
        String user_name = TokenUtil.chekaToken(TokenUtil.workToken(jwtToken));
        AccountDTO accountDTO = JSON.parseObject(user_name, AccountDTO.class);
        HousingUserManagementDTO housingUserManagementDTO = JSON.parseObject(housingUserManagement,
                HousingUserManagementDTO.class);
        HousingFileDTO housingFileDTO = JSON.parseObject(newFileDTO, HousingFileDTO.class);
        if (housingUserManagementDTO == null) {
            throw new BusinessException(HousingErrorCode.E_1630102);
        }
        if (!accountDTO.getId().equals(housingUserManagementDTO.getAccountId())) {
            throw new BusinessException(HousingErrorCode.E_1630501);
        }
        housingUserService.updateHousingUserManagement(files, housingUserManagementDTO, housingFileDTO);
        return RestResponse.success(HousingErrorCode.E_1630301.getDesc());
    }

    @Override
    @ApiOperation("删除用户房源信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "housingManagementId", value = "房源ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "accountId", value = "用户ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "housingFileDTO", value = "删除图片对象", required = true, dataType = "HousingFileDTO", paramType = "body") })
    @PostMapping("/bus/delUserManagement/{housingManagementId}/{accountId}")
    public RestResponse<String> delUserManagement(
            @RequestHeader("Authorization") String jwtToken, @PathVariable Long housingManagementId,
            @PathVariable Long accountId, @RequestBody HousingFileDTO housingFileDTO) {
        // 获取当前用户
        String user_name = TokenUtil.chekaToken(TokenUtil.workToken(jwtToken));
        AccountDTO accountDTO = JSON.parseObject(user_name, AccountDTO.class);
        if ("root".equals(accountDTO.getUsername())) { // 如果是管理员
            housingUserService.deleteHousingUserManagement(housingManagementId, housingFileDTO);
            return RestResponse.success(HousingErrorCode.E_1630302.getDesc());
        }
        if (!accountDTO.getId().equals(accountId)) { // 非法用户判断
            throw new BusinessException(HousingErrorCode.E_1630501);
        }
        housingUserService.deleteHousingUserManagement(housingManagementId, housingFileDTO);
        return RestResponse.success(HousingErrorCode.E_1630302.getDesc());
    }

    @Override
    @ApiOperation("获取出租方式")
    @GetMapping("/u/getMode")
    public RestResponse<List<HousingModeDTO>> getMode() {
        return RestResponse.success(housingUserService.getHousingMode());
    }

    @Override
    @ApiOperation("获取房源简介")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "housingManagementId", value = "房源ID", required = true, dataType = "long") })
    @GetMapping("/u/getUserBriefIn/{housingManagementId}")
    public RestResponse<HousingBriefIntroductionDTO> getUserBriefIn(
            @PathVariable("housingManagementId") long housingManagementId) {
        return RestResponse.success(housingUserService.getHousingBriefIntroduction(housingManagementId));
    }
}
