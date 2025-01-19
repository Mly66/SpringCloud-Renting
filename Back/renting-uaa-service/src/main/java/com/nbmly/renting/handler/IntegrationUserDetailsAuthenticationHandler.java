package com.nbmly.renting.handler;

import com.alibaba.fastjson.JSON;
import com.nbmly.renting.account.model.AccountDTO;
import com.nbmly.renting.account.model.AccountLoginDTO;
import com.nbmly.renting.api.AccountFeignApi;
import com.nbmly.renting.common.RestResponse;
import com.nbmly.renting.pojo.UnifiedUserDetails;
import com.nbmly.renting.util.EncryptUtil;
import com.nbmly.renting.utils.ApplicationContextHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class IntegrationUserDetailsAuthenticationHandler {

    /**
     * 认证处理
     *
     * @param domain 用户域 ，如b端用户、c端用户等
     * @param token
     * @return
     */
    public UnifiedUserDetails authentication(String domain, UsernamePasswordAuthenticationToken token) {
        // 1. 取数据
        String username = token.getName();
        if (StringUtils.isBlank(username)) {
            throw new BadCredentialsException("账号为空");
        }
        if (token.getCredentials() == null) {
            throw new BadCredentialsException("密码为空");
        }
        String presentedPassword = token.getCredentials().toString();

        // 2. 远程调用account，进行账号密码校验
        AccountFeignApi accountFeignApi = (AccountFeignApi) ApplicationContextHelper.getBean(AccountFeignApi.class);
        AccountLoginDTO loginDTO = new AccountLoginDTO();
        loginDTO.setUsername(EncryptUtil.encodeUTF8StringBase64(username));
        loginDTO.setPassword(presentedPassword);
        loginDTO.setDomain(domain);
        RestResponse<AccountDTO> responseAccountDto = accountFeignApi.login(loginDTO);
        // 3.异常处理
        if (responseAccountDto == null) {
            throw new BadCredentialsException("登录失败！");
        }

        AccountDTO accountDTO = responseAccountDto.getData();
        List<GrantedAuthority> authorities = new ArrayList<>();
        accountDTO.getPowers().forEach(code -> {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(code);
            authorities.add(simpleGrantedAuthority);
        });
        // 4.登录成功，把用户数据封装到 UnifiedUserDetails 对象中
        UnifiedUserDetails unifiedUserDetails = new UnifiedUserDetails(JSON.toJSONString(accountDTO), presentedPassword,
                authorities);
        unifiedUserDetails.setMobile(accountDTO.getCellPhone());
        return unifiedUserDetails;
    }

}