package io.security.corespringsecurity.service.impl;

import io.security.corespringsecurity.domain.dto.RoleResourcesDto;
import io.security.corespringsecurity.domain.entity.Resource;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.domain.entity.RoleResource;
import io.security.corespringsecurity.repository.ResourcesRepository;
import io.security.corespringsecurity.repository.RoleRepository;
import io.security.corespringsecurity.repository.RoleResourceRepository;
import io.security.corespringsecurity.service.ResourcesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourcesServiceImpl implements ResourcesService {

    private final ResourcesRepository resourcesRepository;
    private final RoleRepository roleRepository;
    private final RoleResourceRepository roleResourceRepository;

    @Transactional
    public Resource getResources(long id) {
        return resourcesRepository.findById(id).orElse(new Resource());
    }

    @Transactional
    public List<Resource> getResources() {
        return resourcesRepository.findAll(Sort.by(Sort.Order.asc("orderNum")));
    }

    @Transactional
    public void createRoleAndResources(RoleResourcesDto roleResourcesDto){

        List<Role> roles = roleResourcesDto.getRoles();
        roleRepository.saveAll(roles);

        ModelMapper modelMapper = new ModelMapper();
        Resource resource = modelMapper.map(roleResourcesDto, Resource.class);

        resourcesRepository.save(resource);
        roles.forEach(role -> {
            RoleResource roleResource = RoleResource.builder()
                    .role(role)
                    .resource(resource)
                    .build();
            roleResourceRepository.save(roleResource);
        });
    }

    @Transactional
    public void deleteResources(long id) {
        resourcesRepository.deleteById(id);
    }
}
