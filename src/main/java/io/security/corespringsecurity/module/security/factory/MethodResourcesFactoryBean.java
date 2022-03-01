package io.security.corespringsecurity.module.security.factory;

import io.security.corespringsecurity.module.service.impl.SecurityResourceService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class MethodResourcesFactoryBean implements FactoryBean<LinkedHashMap<String, List<ConfigAttribute>>> {

    private SecurityResourceService securityResourceService;
    private LinkedHashMap<String, List<ConfigAttribute>> resourceMap;

    public MethodResourcesFactoryBean(SecurityResourceService securityResourceService) {
        this.securityResourceService = securityResourceService;
    }

    private void init() {
        resourceMap = securityResourceService.getMethodResourceList();
    }

    @Override
    public boolean isSingleton() {
        return true; //1개만 있어서 true로 처리
    }

    @Override
    public LinkedHashMap<String, List<ConfigAttribute>> getObject() {
        if(Objects.isNull(resourceMap)) {
            init();
        }
        return resourceMap;
    }

    @Override
    public Class<?> getObjectType() {
        return LinkedHashMap.class;
    }
}
