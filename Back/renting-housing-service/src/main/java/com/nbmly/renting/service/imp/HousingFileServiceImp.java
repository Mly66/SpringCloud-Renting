package com.nbmly.renting.service.imp;

import com.nbmly.renting.BusinessException;
import com.nbmly.renting.common.HousingErrorCode;
import com.nbmly.renting.housing.model.HousingFileDTO;
import com.nbmly.renting.mapper.HousingManagementMapper;
import com.nbmly.renting.pojo.HousingManagement;
import com.nbmly.renting.service.HousingFileService;
import com.nbmly.renting.common.RestResponse;
import com.nbmly.renting.utils.FastDFSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class HousingFileServiceImp implements HousingFileService {

    @Autowired
    private HousingManagementMapper housingManagementMapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 上传文件
     * 
     * @param file 文件
     * @return HousingFileDTO
     */
    @Override
    public HousingFileDTO upload(MultipartFile file) {
        byte[] buffFile = new byte[0];
        HousingFileDTO housingFileDTOTemp = null;
        try {
            buffFile = file.getBytes();
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件大小
            Long fileSize = file.getSize();
            String fileType = file.getContentType();
            // 获取文件扩展名
            String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 上传到分布式文件系统当中
            String[] result = FastDFSUtil.upload(buffFile, fileExtName);

            // 封装数据返回
            housingFileDTOTemp = new HousingFileDTO();
            housingFileDTOTemp.setGroupName(result[0]);
            housingFileDTOTemp.setFileName(result[1]);

        } catch (Exception e) {
            logger.error("文件上传失败");
            throw new BusinessException(HousingErrorCode.E_1630201);
        }

        if (housingFileDTOTemp == null) {
            logger.error("文件上传失败");
            throw new BusinessException(HousingErrorCode.E_1630201);
        }

        return housingFileDTOTemp;
    }

    /**
     * 文件删除
     * 
     * @param housingFileDTO 文件DTO对象
     * @return
     */
    @Override
    public RestResponse<String> fileDel(HousingFileDTO housingFileDTO) {
        try {
            FastDFSUtil.delete(housingFileDTO.getGroupName(), housingFileDTO.getFileName());
        } catch (Exception e) {
            logger.error("文件删除失败");
            throw new BusinessException(HousingErrorCode.E_1630202);
        }

        return RestResponse.success("删除成功");
    }
}
