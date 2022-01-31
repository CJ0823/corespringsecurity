package io.security.corespringsecurity.service;

import io.security.corespringsecurity.domain.dto.RoleResourcesDto;
import io.security.corespringsecurity.domain.entity.Resource;

import java.util.List;

public interface ResourcesService {

    Resource getResources(long id);

    List<Resource> getResources();

    void createRoleAndResources(RoleResourcesDto roleResourcesDto);

    void deleteResources(long id);
}
