package io.security.corespringsecurity.security.listener;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.AccountRoles;
import io.security.corespringsecurity.domain.entity.Resources;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final ResourcesRepository resourcesRepository;
    private final RoleResourceRepository roleResourceRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }

        setupSecurityResources();

        alreadySetup = true;
    }


    private void setupSecurityResources() {
        Set<Role> roles = new HashSet<>();
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", "관리자");
        roles.add(adminRole);
        createResourceIfNotFound("/admin/**", "", roles, "url");
        Account account = createUserIfNotFound("admin", "pass", "admin@gmail.com", 10, roles);

//        Set<Role> roles1 = new HashSet<>();
//
//        Role managerRole = createRoleIfNotFound("ROLE_MANAGER", "매니저");
//        roles1.add(managerRole);
//        createResourceIfNotFound("io.security.corespringsecurity.aopsecurity.method.AopMethodService.methodTest", "", roles1, "method");
//        createResourceIfNotFound("io.security.corespringsecurity.aopsecurity.method.AopMethodService.innerCallMethodTest", "", roles1, "method");
//        createResourceIfNotFound("execution(* io.security.corespringsecurity.aopsecurity.pointcut.*Service.*(..))", "", roles1, "pointcut");
//        createUserIfNotFound("manager", "pass", "manager@gmail.com", 20, roles1);
//
//        Set<Role> roles3 = new HashSet<>();
//
//        Role childRole1 = createRoleIfNotFound("ROLE_USER", "회원");
//        roles3.add(childRole1);
//        createResourceIfNotFound("/users/**", "", roles3, "url");
//        createUserIfNotFound("user", "pass", "user@gmail.com", 30, roles3);

    }

    @Transactional
    public Role createRoleIfNotFound(String roleName, String roleDesc) {

        Role role = roleRepository.findByRoleName(roleName);

        if (role == null) {
            role = Role.builder()
                    .roleName(roleName)
                    .roleDesc(roleDesc)
                    .build();
        }
        return roleRepository.save(role);
    }

    @Transactional
    public Account createUserIfNotFound(String userName, String password, String email, int age, List<Role> roles) {

        Account account = Optional.ofNullable(accountRepository.findByUsername(userName))
                .orElseGet(() -> Account.builder()
                        .username(userName)
                        .email(email)
                        .age(age)
                        .password(passwordEncoder.encode(password))
                        .build());
        List<AccountRoles> accountRoles = roles.stream().map(role ->
                AccountRoles.builder()
                        .role(role)
                        .account(account)
                        .build()
        ).collect(Collectors.toList());
        accountRoleRepository.saveAll(accountRoles);

        return accountRepository.save(account);
    }


    @Transactional
    public Resources createResourceIfNotFound(String resourceName, String httpMethod, List<Role> roles, String resourceType) {
        Resources resources = resourcesRepository.findByResourceNameAndHttpMethod(resourceName, httpMethod);

            resources = Resources.builder()
                    .resourceName(resourceName)
                    .roleSet(roleSet)
                    .httpMethod(httpMethod)
                    .resourceType(resourceType)
                    .orderNum(count.incrementAndGet())
                    .build();

        return resourcesRepository.save(resources);
    }
}
