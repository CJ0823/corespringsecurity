package io.security.corespringsecurity.module.service.impl;

import io.security.corespringsecurity.module.domain.entity.AccountRole;
import io.security.corespringsecurity.module.domain.entity.Role;
import io.security.corespringsecurity.module.repository.AccountRoleRepository;
import io.security.corespringsecurity.module.repository.RoleRepository;
import io.security.corespringsecurity.module.service.RoleService;
import io.security.corespringsecurity.module.service.dto.AccountRoleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final AccountRoleRepository accountRoleRepository;

    public Role getRole(long id) {
        return roleRepository.findById(id).orElse(new Role());
    }

    public List<Role> getRoles() {

        return roleRepository.findAll();
    }

    @Transactional
    public void createRole(Role role){

        roleRepository.save(role);
    }

    @Transactional
    public void deleteRole(long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<AccountRoleDto> getRolesWithAccount(Long accountId) {
        List<Role> roles = getRoles();
        List<AccountRole> accountRoles = accountRoleRepository.findAllByAccountId(accountId);

        return roles.stream().map(role -> {
            boolean whetherAccountHasRole = accountRoles.stream()
                    .anyMatch(accountRole -> accountRole.getRole().getId().equals(role.getId()));
            return AccountRoleDto.builder()
                    .roleId(role.getId())
                    .roleName(role.getRoleName())
                    .isChecked(whetherAccountHasRole)
                    .build();
        }).collect(Collectors.toList());
    }
}
