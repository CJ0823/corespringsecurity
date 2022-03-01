package io.security.corespringsecurity.module.repository;

import io.security.corespringsecurity.module.domain.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourcesRepository extends JpaRepository<Resource, Long> {

    Optional<Resource> findByResourceNameAndHttpMethod(String resourceName, String httpMethod);

    List<Resource> findAllByResourceType(String resourceType);
}
