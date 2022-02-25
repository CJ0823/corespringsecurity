package io.security.corespringsecurity.module.service.impl;

import io.security.corespringsecurity.module.domain.entity.RoleHierarchy;
import io.security.corespringsecurity.module.repository.RoleHierarchyRepository;
import io.security.corespringsecurity.module.service.RoleHierarchyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class RoleHierarchyServiceImpl implements RoleHierarchyService {

    private final RoleHierarchyRepository roleHierarchyRepository;

    @Transactional
    @Override
    public String findAllHierarchy() {
        List<RoleHierarchy> roleHierarchies = roleHierarchyRepository.findAll();

        Iterator<RoleHierarchy> itr = roleHierarchies.iterator();
        StringBuilder concatedRoles = new StringBuilder();
        while(itr.hasNext()) {
            RoleHierarchy roleHierarchy = itr.next();
            if (!Objects.isNull(roleHierarchy.getParentName())) {
                concatedRoles.append(roleHierarchy.getParentName().getChildName());
                concatedRoles.append(" > "); //spring security 규칙
                concatedRoles.append(roleHierarchy.getChildName());
                concatedRoles.append("\n");
            }
        }
        return concatedRoles.toString();
    }
}
