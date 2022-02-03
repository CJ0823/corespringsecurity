package io.security.corespringsecurity.module.service;

import io.security.corespringsecurity.module.service.dto.RoleResourcesDto;
import io.security.corespringsecurity.module.domain.entity.Resource;

import java.util.List;

public interface ResourcesService {

    Resource getResources(long id);

    List<Resource> getResources();

    void createRoleAndResources(RoleResourcesDto roleResourcesDto);

    void deleteResources(long id);
}
