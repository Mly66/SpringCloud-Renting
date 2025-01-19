package com.nbmly.renting.mapper;

import com.nbmly.renting.account.model.AccountLoginDTO;
import com.nbmly.renting.account.model.AccountRegisterDTO;
import com.nbmly.renting.pojo.Account;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {
    Account login(AccountLoginDTO accountLoginDTO);

    int register(AccountRegisterDTO accountRegisterDTO);

    int isAccountUsername(@Param("username") String username);

    Account getById(Long id);

    int revise(AccountRegisterDTO accountRegisterDTO);

    List<Account> getAccountList(@Param("page") int page, @Param("rows") int rows, @Param("username") String username);

    int getAccountCounts(String username);

    void reviseAccountState(@Param("accountId") Long accountId, @Param("state") Integer state);
}
