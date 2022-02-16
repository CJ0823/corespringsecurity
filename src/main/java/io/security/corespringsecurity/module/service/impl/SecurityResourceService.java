package io.security.corespringsecurity.module.service.impl;

import io.security.corespringsecurity.module.domain.entity.Resource;
import io.security.corespringsecurity.module.domain.entity.RoleResource;
import io.security.corespringsecurity.module.repository.ResourcesRepository;
import io.security.corespringsecurity.module.repository.RoleResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RequiredArgsConstructor
public class SecurityResourceService {

    private final ResourcesRepository resourcesRepository;
    private final RoleResourceRepository roleResourceRepository;

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {

        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();
        List<Resource> resources = resourcesRepository.findAll();
        resources.forEach(resource -> {
            List<ConfigAttribute> configAttributes = new ArrayList<>();
            Long resourceId = resource.getId();
            List<RoleResource> roleResources = roleResourceRepository.findAllByResourceId(resourceId);
            roleResources.forEach(roleResource -> {
                configAttributes.add(new SecurityConfig(roleResource.getRole().getRoleName())); //ConfigAttribute 타입의 구현체인 SecurityConfig를 넣어준다.
                result.put(new AntPathRequestMatcher(resource.getResourceName()), configAttributes);
            });
        });
        return result;
    }
}
