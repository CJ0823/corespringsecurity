package io.security.corespringsecurity.module.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.security.corespringsecurity.module.service.dto.UserQdDto;

import javax.persistence.EntityManager;
import java.util.List;

import static io.security.corespringsecurity.module.domain.entity.QAccount.account;
import static io.security.corespringsecurity.module.domain.entity.QAccountRole.accountRole;
import static io.security.corespringsecurity.module.domain.entity.QRole.role;

public class AccountRoleRepositoryImpl implements AccountRoleRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public AccountRoleRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<UserQdDto> getUsers() {
        return jpaQueryFactory
                .select(Projections.constructor(
                        UserQdDto.class,
                        account.id,
                        account.username,
                        account.email,
                        account.age,
                        account.password,
                        role.roleName
                ))
                .from(accountRole)
                .innerJoin(accountRole.account, account)
                .innerJoin(accountRole.role, role)
                .fetch();
    }
}
