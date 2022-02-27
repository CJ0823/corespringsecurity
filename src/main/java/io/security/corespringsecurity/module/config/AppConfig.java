package io.security.corespringsecurity.module.config;

import io.security.corespringsecurity.module.repository.AccessIpRepository;
import io.security.corespringsecurity.module.repository.ResourcesRepository;
import io.security.corespringsecurity.module.repository.RoleResourceRepository;
import io.security.corespringsecurity.module.service.impl.SecurityResourceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    @Bean
    public SecurityResourceService securityResourceService(ResourcesRepository resourcesRepository, RoleResourceRepository roleResourceRepository, AccessIpRepository accessIpRepository) {
        SecurityResourceService securityResourceService = new SecurityResourceService(resourcesRepository, roleResourceRepository, accessIpRepository);
        return securityResourceService;
    }
}
