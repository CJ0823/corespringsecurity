package io.security.corespringsecurity.service.impl;

import io.security.corespringsecurity.domain.dto.AccountDto;
import io.security.corespringsecurity.domain.dto.AccountRoleDto;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.AccountRole;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.repository.AccountRepository;
import io.security.corespringsecurity.repository.AccountRoleRepository;
import io.security.corespringsecurity.repository.RoleRepository;
import io.security.corespringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AccountRepository accountRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void createUser(Account account){

        Role role = roleRepository.findByRoleName("ROLE_USER");
        accountRepository.save(account);

        AccountRole accountRole = AccountRole.builder()
                .account(account)
                .role(role)
                .build();
        accountRoleRepository.save(accountRole);

    }

    @Transactional
    @Override
    public void modifyUser(AccountRoleDto accountRoleDto){

        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountRoleDto, Account.class);

        //role처리
        List<Role> roles = accountRoleDto.getRoles();
        if(!roles.isEmpty()) {
            roles.forEach(role -> {
                AccountRole accountRole = AccountRole.builder()
                        .role(role)
                        .account(account)
                        .build();
                accountRoleRepository.save(accountRole);
            });
        }

        account.setPassword(passwordEncoder.encode(accountRoleDto.getPassword()));
        accountRepository.save(account);

    }

    @Transactional
    public AccountDto getUser(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("id = " + id));

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(account, AccountDto.class);
    }

    @Transactional
    public List<Account> getUsers() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        accountRepository.deleteById(id);
    }
}