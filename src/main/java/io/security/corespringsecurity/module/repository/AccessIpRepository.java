package io.security.corespringsecurity.module.repository;

import io.security.corespringsecurity.module.domain.entity.AccessIp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessIpRepository extends JpaRepository<AccessIp, Long> {

    AccessIp findByIpAddress(String ipAddress);
}
