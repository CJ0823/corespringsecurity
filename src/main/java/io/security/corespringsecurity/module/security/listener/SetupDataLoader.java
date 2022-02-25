package io.security.corespringsecurity.module.security.listener;

import io.security.corespringsecurity.module.domain.entity.*;
import io.security.corespringsecurity.module.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
    private final RoleHierarchyRepository roleHierarchyRepository;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }

        setupSecurityResources();

        alreadySetup = true;
    }

    //DB상 초기 데이터를 넣어주기 위한 부분일 뿐, 만약 DB상에 데이터가 있다면 필수적인 코드는 아님
    private void setupSecurityResources() {
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
        Role managerRole = createRoleIfNotFound("ROLE_MANAGER");
        Role userRole = createRoleIfNotFound("ROLE_USER");
        createResourceIfNotFound("/admin/**", "", adminRole, "url");
        createUserIfNotFound("admin", "pass", "admin@gmail.com", 10, adminRole);
        createUserIfNotFound("manager", "pass", "manager@gmail.com", 11, managerRole);
        createUserIfNotFound("user", "pass", "user@gmail.com", 12, userRole);
        createRoleHierarchyIfNotFound(userRole, adminRole);
        createRoleHierarchyIfNotFound(managerRole, adminRole);

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
    public Role createRoleIfNotFound(String roleName) {

        Role role = roleRepository.findByRoleName(roleName)
                .orElse(Role.builder()
                        .roleName(roleName)
                        .build());
        return roleRepository.save(role);
    }

    @Transactional
    public Account createUserIfNotFound(String userName, String password, String email, int age, Role role) {

        return accountRepository.findByEmail(email)
                .orElseGet(() -> {
                    Account newAccount =  Account.builder()
                            .username(userName)
                            .email(email)
                            .age(age)
                            .password(passwordEncoder.encode(password))
                            .build();
                    accountRepository.save(newAccount);

                    AccountRole accountRole =
                            AccountRole.builder()
                                    .role(role)
                                    .account(newAccount)
                                    .build();
                    accountRoleRepository.save(accountRole);
                    return newAccount;
                });
    }


    @Transactional
    public Resource createResourceIfNotFound(String resourceName, String httpMethod, Role role, String resourceType) {
        return resourcesRepository.findByResourceNameAndHttpMethod(resourceName, httpMethod)
                .orElseGet(() ->
                {
                    Resource newResource = Resource.builder()
                            .resourceName(resourceName)
                            .httpMethod(httpMethod)
                            .resourceType(resourceType)
                            .build();

                    resourcesRepository.save(newResource);

                        RoleResource newRoleResource = RoleResource.builder()
                                .resource(newResource)
                                .role(role)
                                .build();
                        roleResourceRepository.save(newRoleResource);
                    return newResource;
                });
    }

    @Transactional
    public void createRoleHierarchyIfNotFound(Role childRole, Role parentRole) {
        //parent
        RoleHierarchy roleHierarchy = roleHierarchyRepository.findByChildName(parentRole.getRoleName());
        if (roleHierarchy == null) {
            roleHierarchy = RoleHierarchy.builder().childName(parentRole.getRoleName()).build();
        }
        RoleHierarchy parentRoleHierarchy = roleHierarchyRepository.save(roleHierarchy);

        //child
        roleHierarchy = roleHierarchyRepository.findByChildName(childRole.getRoleName());
        if (roleHierarchy == null) {
            roleHierarchy = RoleHierarchy.builder().childName(childRole.getRoleName()).build();
        }
        RoleHierarchy childRoleHierarchy = roleHierarchyRepository.save(roleHierarchy);
        childRoleHierarchy.setParentName(parentRoleHierarchy);
    }
}
