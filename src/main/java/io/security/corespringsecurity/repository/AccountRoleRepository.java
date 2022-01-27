package io.security.corespringsecurity.repository;

import io.security.corespringsecurity.domain.entity.AccountRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRoleRepository extends JpaRepository<AccountRoles, Long> {
}
