package io.security.corespringsecurity.repository;

import io.security.corespringsecurity.domain.entity.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleResourceRepository extends JpaRepository<RoleResource, Long> {

    List<RoleResource> findAllByRoleId(Long RoleId);
}
