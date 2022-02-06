package io.security.corespringsecurity.module.service.impl;

import io.security.corespringsecurity.module.repository.RoleResourceRepository;
import io.security.corespringsecurity.module.service.RoleResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleResourceServiceImpl implements RoleResourceService {

    private final RoleResourceRepository roleResourceRepository;

    @Override
    public void deleteRoleResourcesByResourceId(Long resourceId) {
        roleResourceRepository.deleteAllByResourceId(resourceId);
    }

    @Override
    public void deleteRoleSourcesByRoleId(Long roleId) {
        roleResourceRepository.deleteAllByRoleId(roleId);
    }
}
