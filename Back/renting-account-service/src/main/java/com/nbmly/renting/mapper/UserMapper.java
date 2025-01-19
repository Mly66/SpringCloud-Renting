package com.nbmly.renting.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    String lookupPowers(@Param("accountId") Long accountId);

    int powerRegister(@Param("accountId") Long accountId);

    int revisePower(@Param("accountId") Long accountId);

    int reviseUserPower(@Param("accountId") Long accountId, @Param("powerId") Long powerId);
}
