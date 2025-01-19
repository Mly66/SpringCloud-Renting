package com.nbmly.renting.account;

import com.nbmly.renting.common.RestResponse;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

public interface AccountModAPI {
    /**
     * 报错收藏信息
     * 
     * @param jwtToken
     * @param value    收藏房屋信息
     * @return
     */
    RestResponse<String> saveCollection(String jwtToken, String value);

    /**
     * 删除收藏信息
     * 
     * @return
     */
    RestResponse<String> delCollection(String jwtToken, String info);

    /**
     * 获取收藏信息
     * 
     * @param jwtToken
     * @return
     */
    RestResponse<Set<String>> getCollection(String jwtToken);

    /**
     * 查看当前用户是否收藏
     * 
     * @param jwtToken
     * @param info     收藏房屋信息
     * @return
     */
    RestResponse<Boolean> isCollection(String jwtToken, String info);
}
