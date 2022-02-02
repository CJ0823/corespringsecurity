package io.security.corespringsecurity.module.service;

import io.security.corespringsecurity.module.domain.dto.AccountDto;
import io.security.corespringsecurity.module.domain.dto.AccountRoleDto;
import io.security.corespringsecurity.module.domain.entity.Account;

import java.util.List;

public interface UserService {

    void createUser(Account account);

    void modifyUser(AccountRoleDto accountRoleDto);

    List<AccountRoleDto> getUsers();

    AccountDto getUser(Long id);

    void deleteUser(Long idx);
}
