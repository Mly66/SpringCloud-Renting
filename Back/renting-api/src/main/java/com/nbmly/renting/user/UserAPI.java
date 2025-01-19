package com.nbmly.renting.user;

import com.nbmly.renting.common.RestResponse;

public interface UserAPI {
    /**
     * 修改当前用户权限
     * 
     * @param jwtToken
     * @return
     */
    RestResponse<String> revisePower(String jwtToken);

    /**
     * 管理员修改用户权限
     * 
     * @param accountId
     * @param powerId
     * @return
     */
    RestResponse<String> reviseByIdPower(Long accountId, Long powerId);
}
