package io.security.corespringsecurity.module.repository;

import io.security.corespringsecurity.module.domain.entity.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRoleRepository extends JpaRepository<AccountRole, Long>, AccountRoleRepositoryCustom {
    List<AccountRole> findAllByAccountId(Long accountId);

    void deleteByAccountId(Long accountId);
}
