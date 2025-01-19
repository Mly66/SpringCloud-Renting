package com.nbmly.renting.im;

import com.nbmly.renting.common.RestResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

public interface ImAPI {
    /**
     * 查询当前用户给谁发过消息
     * 
     * @param jwtToken
     * @return
     */
    RestResponse<Map<String, Object>> getFirstMsg(String jwtToken);

    /**
     * 查询当前一对一聊天记录
     * 
     * @param jwtToken
     * @param toId
     * @param page
     * @param rows
     * @return
     */
    RestResponse<Map<String, Object>> getFromAndToMsg(String jwtToken, Long toId, Integer page, Integer rows);
}
