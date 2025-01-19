package com.nbmly.renting.service.imp;

import com.nbmly.renting.BusinessException;
import com.nbmly.renting.common.HousingCommon;
import com.nbmly.renting.common.HousingErrorCode;
import com.nbmly.renting.housing.model.*;
import com.nbmly.renting.mapper.HousingBriefIntroductionMapper;
import com.nbmly.renting.mapper.HousingManagementMapper;
import com.nbmly.renting.mapper.HousingModeMapper;
import com.nbmly.renting.mapper.HousingUserMapper;
import com.nbmly.renting.pojo.HousingBriefIntroduction;
import com.nbmly.renting.pojo.HousingManagement;
import com.nbmly.renting.pojo.HousingMode;
import com.nbmly.renting.pojo.HousingUser;
import com.nbmly.renting.service.HousingFileService;
import com.nbmly.renting.service.HousingUserService;
import com.nbmly.renting.utils.HousingUtil;
import javafx.scene.input.DataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class HousingUserServiceImp implements HousingUserService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HousingCommonService housingCommonService;
    @Autowired
    private HousingManagementMapper housingManagementMapper;
    @Autowired
    private HousingModeMapper housingModeMapper;
    @Autowired
    private HousingUserMapper housingUserMapper;
    @Autowired
    private HousingBriefIntroductionMapper housingBriefIntroductionMapper;
    @Autowired
    private HousingFileService housingFileService;

    /**
     * 获取用户房子信息
     * 
     * @param accountId 账户Id
     * @param page      页数
     * @return
     */
    @Override
    public List<HousingUserManagementDTO> getHousingUserInformation(long accountId, int page) {
        // 分页处理
        page = (page - 1) * HousingCommon.RECORD;

        // 获取用户房子信息
        List<HousingUser> housingUsers = housingUserMapper.getHousingUser(accountId, page, HousingCommon.RECORD);

        // 转换成DTO对象
        List<HousingUserManagementDTO> housingUserManagementDTOS = HousingUtil
                .formatHousingUserManagementDTO(housingUsers);

        // 返回结果
        return housingUserManagementDTOS;
    }

    /**
     * 赋值HousingManagement文件信息
     * 
     * @param file              上传文件
     * @param housingManagement 房源pojo
     */
    public void uploadInformation(MultipartFile[] file, HousingManagement housingManagement) {
        // 保存图片
        for (int i = 0; i < file.length; i++) {
            HousingFileDTO housingFileDTO = housingFileService.upload(file[i]);
            housingManagement.setGroupName(housingManagement.getGroupName() + housingFileDTO.getGroupName() + ",");
            housingManagement.setFileName(housingManagement.getFileName() + housingFileDTO.getFileName() + ",");

            if (i == file.length - 1) {
                housingManagement.setGroupName(
                        housingManagement.getGroupName().substring(0, housingManagement.getGroupName().length() - 1));
                housingManagement.setFileName(
                        housingManagement.getFileName().substring(0, housingManagement.getFileName().length() - 1));
            }
        }
    }

    /**
     * 保存用户房源信息
     * 
     * @param housingUserManagementDTO 房源DTO对象
     * @return
     */
    @Override
    @Transactional // 添加本地事务
    public String saveHousingUserManagement(MultipartFile[] file, HousingUserManagementDTO housingUserManagementDTO) {
        try {
            // 获取出租方式Id
            HousingMode housingMode = housingModeMapper.queryMode(housingUserManagementDTO.getMode());

            // DTO转实体类对象
            HousingManagement housingManagement = new HousingManagement();
            BeanUtils.copyProperties(housingUserManagementDTO, housingManagement);
            housingManagement.setHousingModeId(housingMode.getId());

            if (file.length <= 0) {
                housingManagement.setGroupName(null);
                housingManagement.setFileName(null);
            } else {
                housingManagement.setGroupName("");
                housingManagement.setFileName("");
            }
            // 上传图片
            this.uploadInformation(file, housingManagement);

            // 保存房源信息
            housingManagementMapper.saveHousingManagement(housingManagement);
            housingManagement.setGroupName("");
            housingManagement.setFileName("");

            // 保存出租人信息
            housingUserMapper.saveHousing(housingUserManagementDTO.getAccountId(), housingManagement.getId());

            // DTO转实体类对象
            HousingBriefIntroduction housingBriefIntroduction = new HousingBriefIntroduction();
            HousingBriefIntroductionDTO housingBriefIntroductionDTO = housingUserManagementDTO
                    .getHousingBriefIntroductionDTO();
            BeanUtils.copyProperties(housingBriefIntroductionDTO, housingBriefIntroduction);
            housingBriefIntroduction.setHousingManagementId(housingManagement.getId());
            // 保存房源简介
            housingBriefIntroductionMapper.saveHousingBriefIntroduction(housingBriefIntroduction);

        } catch (Exception e) {
            logger.error("上传房源失败");
            throw new BusinessException(HousingErrorCode.E_1630101);
        }

        return "上传房源成功";
    }

    /**
     * 获取修改要删除的图片
     * 
     * @param delGroups 删除图片组
     * @param newGroups 删除图片路径
     * @param newFiles  修改图片组
     */
    private void getDelImgFile(
            ArrayList<String> delGroups, ArrayList<String> delFiles,
            String[] newGroups, String[] newFiles) {
        // 获取要删除的图片
        for (int i = 0; i < newFiles.length; i++) {
            Iterator<String> fileIterator = delFiles.iterator();
            Iterator<String> groupsIterator = delGroups.iterator();
            while (fileIterator.hasNext()) {
                String s = fileIterator.next();
                groupsIterator.next();
                if (newFiles[i].trim().equals(s.trim())) {
                    fileIterator.remove();
                    groupsIterator.remove();
                }
            }
        }
    }

    /**
     * 修改房屋信息
     * 
     * @param files
     * @param housingUserManagementDTO
     * @param housingFileDTO
     * @return
     */
    @Caching(put = {
            @CachePut(cacheNames = {
                    "housing" }, key = "#housingUserManagementDTO.housingManagementId", unless = "#result == null")
    }, evict = {
            @CacheEvict(cacheNames = { "housing::introduction" }, key = "#housingUserManagementDTO.housingManagementId")
    })
    @Transactional
    @Override
    public HousingUserManagementDTO updateHousingUserManagement(MultipartFile[] files,
            HousingUserManagementDTO housingUserManagementDTO, HousingFileDTO housingFileDTO) {
        System.out.println(housingFileDTO);
        ArrayList<String> delGroups = null;
        ArrayList<String> delFiles = null;
        // 要删除的图片组
        if (housingUserManagementDTO.getHousingFileDTO().getFileName() != null) {
            // 获取原有的图片组
            delGroups = new ArrayList<>(
                    Arrays.asList(housingUserManagementDTO.getHousingFileDTO().getGroupName().split(",")));
            delFiles = new ArrayList<>(
                    Arrays.asList(housingUserManagementDTO.getHousingFileDTO().getFileName().split(",")));
            // 获取最新的图片组
            if (housingFileDTO != null) {
                String[] newGroups = housingFileDTO.getGroupName().split(",");
                String[] newFiles = housingFileDTO.getFileName().split(",");
                this.getDelImgFile(delGroups, delFiles, newGroups, newFiles);
            }
        }
        // DTO转实体类对象
        // 房子pojo
        HousingManagement housingManagement = new HousingManagement();
        BeanUtils.copyProperties(housingUserManagementDTO, housingManagement);
        housingManagement.setId(housingUserManagementDTO.getHousingManagementId());
        if (housingFileDTO == null) {
            housingManagement.setGroupName("");
            housingManagement.setFileName("");
        } else {
            housingManagement.setGroupName(housingFileDTO.getGroupName() + ",");
            housingManagement.setFileName(housingFileDTO.getFileName() + ",");
        }
        if (housingFileDTO != null && housingManagement.getGroupName().length() > 1 && files.length == 0) {
            housingManagement.setGroupName(
                    housingManagement.getGroupName().substring(0, housingManagement.getGroupName().length() - 1));
            housingManagement.setFileName(
                    housingManagement.getFileName().substring(0, housingManagement.getFileName().length() - 1));
        }

        // 查找出租方式
        HousingMode housingMode = housingModeMapper.queryMode(housingManagement.getMode());
        housingManagement.setHousingModeId(housingMode.getId());
        // 上传图片
        this.uploadInformation(files, housingManagement);
        if (housingManagement.getGroupName() == null || "".equals(housingManagement.getGroupName().trim())) {
            housingManagement.setGroupName(null);
            housingManagement.setFileName(null);
        }
        // 修改房子数据库数据
        housingManagementMapper.updateHousingManagement(housingManagement);

        // 房子简介pojo
        HousingBriefIntroduction housingBriefIntroduction = new HousingBriefIntroduction();
        BeanUtils.copyProperties(housingUserManagementDTO.getHousingBriefIntroductionDTO(), housingBriefIntroduction);
        housingBriefIntroduction.setHousingManagementId(housingManagement.getId());

        // 修改房子简介数据
        housingBriefIntroductionMapper.updateHousingBriefIntroduction(housingBriefIntroduction);

        // 删除上传时要删除的图片
        if (delFiles != null) {
            for (int i = 0; i < delFiles.size(); i++) {
                HousingFileDTO tempHousingFileDTO = new HousingFileDTO();
                tempHousingFileDTO.setGroupName(delGroups.get(i));
                tempHousingFileDTO.setFileName(delFiles.get(i));
                housingFileService.fileDel(tempHousingFileDTO);
            }
        }

        HousingUserManagementDTO housing = null;
        housing = housingCommonService.getByIdHousing(housingUserManagementDTO.getHousingManagementId());

        return housing;
    }

    /**
     * 获取出租方式
     * 
     * @return 出租方式
     */
    @Override
    public List<HousingModeDTO> getHousingMode() {
        List<HousingMode> housingModes = housingModeMapper.queryAll();
        List<HousingModeDTO> housingModeDTOS = new ArrayList<>();
        for (HousingMode housingMode : housingModes) {
            HousingModeDTO housingModeDTO = new HousingModeDTO();
            housingModeDTO.setMode(housingMode.getMode());
            housingModeDTOS.add(housingModeDTO);
        }
        return housingModeDTOS;
    }

    /**
     * 获取房屋简介
     * 
     * @param housingManagementId 房子ID
     * @return
     */
    @Cacheable(cacheNames = { "housing::introduction" }, key = "#housingManagementId", unless = "#result == null")
    @Override
    public HousingBriefIntroductionDTO getHousingBriefIntroduction(long housingManagementId) {
        HousingBriefIntroduction housingBriefIntroduction = housingBriefIntroductionMapper
                .getHousingBriefIntroduction(housingManagementId);

        HousingBriefIntroductionDTO housingBriefIntroductionDTO = new HousingBriefIntroductionDTO();
        if (housingBriefIntroduction == null) {
            return null;
        }
        BeanUtils.copyProperties(housingBriefIntroduction, housingBriefIntroductionDTO);

        System.out.println(housingBriefIntroductionDTO);
        return housingBriefIntroductionDTO;
    }

    /**
     * 删除出租人房屋信息
     * 
     * @param housingManagementId 房屋Id
     * @param housingFileDTO      图片信息
     */
    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = { "housing::introduction" }, key = "#housingManagementId"),
            @CacheEvict(cacheNames = { "housing" }, key = "#housingManagementId")
    })
    public void deleteHousingUserManagement(Long housingManagementId, HousingFileDTO housingFileDTO) {
        // 删除出租人信息
        housingUserMapper.deleteHousingUser(housingManagementId);
        // 删除房屋简介信息
        housingBriefIntroductionMapper.deleteHousingBriefIntroduction(housingManagementId);
        // 删除房屋信息
        housingManagementMapper.deleteHousingManagement(housingManagementId);
        // 删除图片
        if (housingFileDTO.getGroupName() != null) {
            // 获取要删除的图片组
            String[] groupNames = housingFileDTO.getGroupName().trim().split(",");
            String[] fileNames = housingFileDTO.getFileName().trim().split(",");
            this.delImgFiles(groupNames, fileNames);
        }
    }

    /**
     * 删除多张图片
     * 
     * @param groupNames 图片组名
     * @param fileNames  图片路径
     */
    private void delImgFiles(String[] groupNames, String[] fileNames) {
        for (int i = 0; i < fileNames.length; i++) {
            HousingFileDTO housingFileDTO = new HousingFileDTO();
            housingFileDTO.setGroupName(groupNames[i]);
            housingFileDTO.setFileName(fileNames[i]);
            housingFileService.fileDel(housingFileDTO);
        }
    }
}
