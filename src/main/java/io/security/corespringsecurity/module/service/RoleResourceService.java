package io.security.corespringsecurity.module.service;

public interface RoleResourceService {

    void deleteRoleResourcesByResourceId(Long resourceId);

    void deleteRoleSourcesByRoleId(Long roleId);
}
