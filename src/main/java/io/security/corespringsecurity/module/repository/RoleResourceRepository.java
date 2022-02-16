package io.security.corespringsecurity.module.repository;

import io.security.corespringsecurity.module.domain.entity.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleResourceRepository extends JpaRepository<RoleResource, Long> {

    List<RoleResource> findAllByRoleId(Long RoleId);

    void deleteByResourceId(Long resourceId);

    void deleteAllByRoleId(Long roleId);

    void deleteAllByResourceId(Long resourceId);

    List<RoleResource> findAllByResourceId(Long resourceId);

}
