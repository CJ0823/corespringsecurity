package io.security.corespringsecurity.module.service.impl;

import io.security.corespringsecurity.module.domain.entity.Resource;
import io.security.corespringsecurity.module.domain.entity.Role;
import io.security.corespringsecurity.module.domain.entity.RoleResource;
import io.security.corespringsecurity.module.repository.ResourcesRepository;
import io.security.corespringsecurity.module.repository.RoleRepository;
import io.security.corespringsecurity.module.repository.RoleResourceRepository;
import io.security.corespringsecurity.module.service.ResourcesService;
import io.security.corespringsecurity.module.service.dto.RoleResourcesDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResourcesServiceImpl implements ResourcesService {

    private final ResourcesRepository resourcesRepository;
    private final RoleRepository roleRepository;
    private final RoleResourceRepository roleResourceRepository;

    public Resource getResources(long id) {
        return resourcesRepository.findById(id).orElse(new Resource());
    }

    public List<Resource> getResources() {
        return resourcesRepository.findAll();
    }

    @Transactional
    public void createRoleAndResources(RoleResourcesDto roleResourcesDto) {

        //role 처리
        String roleName = roleResourcesDto.getRoleName();
        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new NullPointerException("입력된 이름에 해당하는 권한 정보가 없음"));
        roleRepository.save(role);

        //resource 처리
        ModelMapper modelMapper = new ModelMapper();
        Resource resource = modelMapper.map(roleResourcesDto, Resource.class);

        resourcesRepository.findByResourceNameAndHttpMethod(resource.getResourceName(), resource.getHttpMethod())
                .ifPresentOrElse(foundResource -> {
                            //roleResource 처리
                            createRoleResource(role, foundResource);
                        },
                        () -> {
                            //resource 처리
                            Resource savedResource = resourcesRepository.save(resource);
                            //roleResource 처리
                            createRoleResource(role, savedResource);
                        });

    }

    private void createRoleResource(Role role, Resource foundResource) {
        RoleResource roleResource = RoleResource.builder()
                .role(role)
                .resource(foundResource)
                .build();

        List<RoleResource> foundRoleResources = roleResourceRepository.findAllByResourceIdAndRoleId(foundResource.getId(), role.getId());
        if (foundRoleResources.isEmpty()) {
            roleResourceRepository.save(roleResource);
        }
    }

    @Transactional
    public void deleteResources(long id) {
        resourcesRepository.deleteById(id);
        roleResourceRepository.deleteByResourceId(id);
    }
}
