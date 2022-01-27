package io.security.corespringsecurity.repository;

import io.security.corespringsecurity.domain.entity.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleResourceRepository extends JpaRepository<RoleResource, Long> {
}
