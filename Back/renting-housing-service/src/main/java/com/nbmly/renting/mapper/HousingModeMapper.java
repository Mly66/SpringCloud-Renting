package com.nbmly.renting.mapper;

import com.nbmly.renting.pojo.HousingMode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousingModeMapper {
    HousingMode queryMode(@Param("mode") String mode);

    List<HousingMode> queryAll();
}
