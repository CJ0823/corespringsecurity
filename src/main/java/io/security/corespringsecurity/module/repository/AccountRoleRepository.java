package io.security.corespringsecurity.module.repository;

import io.security.corespringsecurity.module.domain.entity.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRoleRepository extends JpaRepository<AccountRole, Long>, AccountRoleRepositoryCustom {
    void deleteByAccountId(Long accountId);

    Optional<AccountRole> findByAccountId(Long accountId);

    List<AccountRole> findAllByAccountId(Long accountId);

    Optional<AccountRole> findByRoleId(Long roleId);

    void deleteByAccountIdAndRoleId(Long accountId, Long roleId);
}
