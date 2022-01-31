package io.security.corespringsecurity.repository;

import io.security.corespringsecurity.domain.entity.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {
    List<AccountRole> findAllByAccountId(Long accountId);
}
