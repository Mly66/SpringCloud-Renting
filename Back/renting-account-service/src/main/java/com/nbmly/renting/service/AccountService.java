package com.nbmly.renting.service;

import com.nbmly.renting.account.model.AccountDTO;
import com.nbmly.renting.account.model.AccountLoginDTO;
import com.nbmly.renting.account.model.AccountRegisterDTO;
import com.nbmly.renting.pojo.Account;
import com.nbmly.renting.common.RestResponse;

import java.util.List;

public interface AccountService {
    RestResponse<AccountDTO> login(AccountLoginDTO accountLoginDTO);

    RestResponse<String> register(AccountRegisterDTO accountRegisterDTO);

    Account getById(Long id);

    Account revise(AccountRegisterDTO accountRegisterDTO);

    List<Account> getAccountList(int page, int rows, String username);

    Integer getAccountCounts(String username);

    Account reviseAccountState(Long accountId, Integer state);
}
