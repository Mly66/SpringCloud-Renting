package com.nbmly.renting.service;

import com.nbmly.renting.common.RestResponse;
import com.nbmly.renting.pojo.Account;

import java.util.List;

public interface UserService {
    RestResponse<List<String>> lookupPowers(Long accountId);

    void powerRegister(Long accountId);

    Account revisePower(Long accountId);

    Account reviseUserPower(Long accountId, Long powerId);
}
