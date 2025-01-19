package com.nbmly.renting.account;

import com.nbmly.renting.account.model.AccountDTO;
import com.nbmly.renting.account.model.AccountLoginDTO;
import com.nbmly.renting.account.model.AccountRegisterDTO;
import com.nbmly.renting.common.RestResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface AccountAPI {
    /**
     * 用户登录
     * 
     * @param accountLoginDTO 用户登录信息
     * @return
     */
    RestResponse<AccountDTO> login(AccountLoginDTO accountLoginDTO);

    /**
     * 用户注册
     * 
     * @param accountRegisterDTO 用户注册信息
     * @return
     */
    RestResponse<String> register(AccountRegisterDTO accountRegisterDTO);

    /**
     * 获取当前令牌信息
     * 
     * @param jwtToken
     * @return
     */
    RestResponse<AccountDTO> getJwtToken(String jwtToken);

    /**
     * 获取用户信息
     * 
     * @param ids id列表
     * @return
     */
    RestResponse<List<AccountDTO>> getByIdAccount(Long[] ids);

    /**
     * 修改用户信息
     * 
     * @param jwtToken
     * @param accountRegisterDTO 用户信息
     * @return
     */
    RestResponse<String> revise(String jwtToken, AccountRegisterDTO accountRegisterDTO);

    /**
     * 获取用户列表信息
     * 
     * @param page     页码
     * @param rows     页数
     * @param username 模糊查询
     * @return
     */
    RestResponse<List<AccountDTO>> getAccounts(int page, int rows, String username);

    /**
     * 获取用户列表数
     * 
     * @param username 模糊查询
     * @return
     */
    RestResponse<Integer> getAccountCounts(String username);

    /**
     * 管理员修改用户状态
     * 
     * @param accountId 用户id
     * @param state     状态
     * @return
     */
    RestResponse<String> reviseAccountState(Long accountId, Integer state);
}
