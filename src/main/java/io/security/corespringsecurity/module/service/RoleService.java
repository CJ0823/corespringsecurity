package io.security.corespringsecurity.module.service;

import io.security.corespringsecurity.module.domain.entity.Role;
import io.security.corespringsecurity.module.service.dto.AccountRoleDto;

import java.util.List;

public interface RoleService {

    Role getRole(long id);

    List<Role> getRoles();

    void createRole(Role role);

    void deleteRole(long id);

    /**
     * 전체 Role 및 accountId에 연관된 role인지 체크하여 반환
     * @param accountId
     * @return
     */
    List<AccountRoleDto> getRolesWithAccount(Long accountId);
}
