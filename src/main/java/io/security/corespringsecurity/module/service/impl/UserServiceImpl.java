package io.security.corespringsecurity.module.service.impl;

import io.security.corespringsecurity.module.service.dto.AccountDto;
import io.security.corespringsecurity.module.service.dto.UserCreateDto;
import io.security.corespringsecurity.module.service.dto.UserModifyDto;
import io.security.corespringsecurity.module.service.dto.UserQdDto;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Account account = modifyAccount(userModifyDto);

        modifyAccountRole(userModifyDto, account);
    }

    private Account modifyAccount(UserModifyDto userModifyDto) {
        String email = userModifyDto.getEmail();
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("입력한 이메일에 해당하는 계정이 없습니다."));

        ModelMapper mapper = new ModelMapper();
        mapper.map(userModifyDto, account);

        account.setPassword(passwordEncoder.encode(userModifyDto.getPassword()));
        return account;
    }

    private void modifyAccountRole(UserModifyDto userModifyDto, Account account) {
        Long accountId = account.getId();

        AccountRole accountRole = accountRoleRepository.findByAccountId(accountId)
                .orElseThrow(() -> new NullPointerException("account와 매칭되는 role 정보 없음"));

        Long roleId = accountRole.getRole().getId();
        Long newRoleId = userModifyDto.getRoleId();
        if(!roleId.equals(newRoleId)) {
            Role newRole = roleRepository.findById(newRoleId)
                    .orElseThrow(() -> new NullPointerException("입력한 roleId에 해당하는 role 정보 없음"));
            accountRole.setRole(newRole);
        }
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
}