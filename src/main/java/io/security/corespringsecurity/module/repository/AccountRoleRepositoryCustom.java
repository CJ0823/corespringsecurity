package io.security.corespringsecurity.module.repository;

import io.security.corespringsecurity.module.domain.dto.AccountRoleDto;

import java.util.List;

public interface AccountRoleRepositoryCustom {

    List<AccountRoleDto> getUsers();
}
