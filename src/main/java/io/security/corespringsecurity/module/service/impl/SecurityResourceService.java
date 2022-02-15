package io.security.corespringsecurity.module.service.impl;

import io.security.corespringsecurity.module.domain.entity.Resource;
import io.security.corespringsecurity.module.repository.ResourcesRepository;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.LinkedHashMap;
import java.util.List;

public class SecurityResourceService {

    private ResourcesRepository resourcesRepository;

    public void setResourcesRepository(ResourcesRepository resourcesRepository) {
        this.resourcesRepository = resourcesRepository;
    }

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {

        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();
        List<Resource> resources = resourcesRepository.findAll();
        resources.forEach(resource -> {

        });
    }
}
