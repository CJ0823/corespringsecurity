package io.security.corespringsecurity.module.repository;

import io.security.corespringsecurity.module.domain.entity.RoleHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchy, Long> {
    RoleHierarchy findByChildName(String roleName);
}
