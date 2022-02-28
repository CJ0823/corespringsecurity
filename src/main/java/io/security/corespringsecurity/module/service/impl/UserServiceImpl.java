package io.security.corespringsecurity.module.service.impl;

import io.security.corespringsecurity.module.service.dto.*;
import io.security.corespringsecurity.module.domain.entity.Account;
import io.security.corespringsecurity.module.domain.entity.AccountRole;
import io.security.corespringsecurity.module.domain.entity.Role;
import io.security.corespringsecurity.module.repository.AccountRepository;
import io.security.corespringsecurity.module.repository.AccountRoleRepository;
import io.security.corespringsecurity.module.repository.RoleRepository;
import io.security.corespringsecurity.module.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AccountRepository accountRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final static String DEFAULT_ROLE_USER = "ROLE_USER";

    @Transactional
    @Override
    public void createUser(UserCreateDto userCreateDto){

        Account account = createAccount(userCreateDto);

        createAccountRole(account, userCreateDto);

    }

    private Account createAccount(UserCreateDto userCreateDto) {
        ModelMapper mapper = new ModelMapper();
        Account account = new Account();
        mapper.map(userCreateDto, account);

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return account;
    }

    private void createAccountRole(Account account, UserCreateDto userCreateDto) {
        String roleName = userCreateDto.getRoleName();

        Role role = roleRepository.findByRoleName(roleName)
                .orElseGet(() -> Role.builder()
                        .roleName(roleName)
                        .build()
                );

        AccountRole accountRole = AccountRole.builder()
                .account(account)
                .role(role)
                .build();
        accountRoleRepository.save(accountRole);
    }

    @Transactional
    @Override
    public void modifyUser(UserModifyDto userModifyDto){

        modifyAccount(userModifyDto);

        modifyAccountRole(userModifyDto);
    }

    private void modifyAccount(UserModifyDto userModifyDto) {
        String email = userModifyDto.getEmail();
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("입력한 이메일에 해당하는 계정이 없습니다."));

        ModelMapper mapper = new ModelMapper();
        mapper.map(userModifyDto, account);
    }

    private void modifyAccountRole(UserModifyDto userModifyDto) {

        Long accountId = userModifyDto.getId();
        List<AccountRole> accountRoles = accountRoleRepository.findAllByAccountId(accountId);
        List<Long> roleIds = accountRoles.stream().map(accountRole -> accountRole.getRole().getId()).collect(Collectors.toList());

        List<AccountRoleDto> newAccountRoles = userModifyDto.getAccountRoles();
        newAccountRoles.forEach(newAccountRole -> {
            Long roleId = newAccountRole.getRoleId();
            Boolean isChecked = newAccountRole.getIsChecked();
            if(isChecked && !roleIds.contains(roleId)) {
                Role role = Role.builder().id(roleId).build();
                Account account = Account.builder().id(accountId).build();
                AccountRole accountRole = AccountRole.builder()
                        .role(role)
                        .account(account)
                        .build();
                accountRoleRepository.save(accountRole);
            }
            else if(!isChecked) {
                accountRoleRepository.deleteByAccountIdAndRoleId(accountId, roleId);
            }
        });
    }

    @Transactional
    public AccountDto getUser(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("id = " + id));

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(account, AccountDto.class);
    }

    @Transactional
    public List<UserQdDto> getUsers() {
        return accountRoleRepository.getUsers();
    }

    @Override
    public void deleteUser(Long id) {
        accountRepository.deleteById(id);
        accountRoleRepository.deleteByAccountId(id);
    }

    @Override
    @Secured("ROLE_MANAGER")
    public void order() {
        System.out.println("order");
    }
}