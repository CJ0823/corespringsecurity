package io.security.corespringsecurity.repository;

import io.security.corespringsecurity.domain.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResourcesRepository extends JpaRepository<Resource, Long> {

    Optional<Resource> findByResourceNameAndHttpMethod(String resourceName, String httpMethod);

}
