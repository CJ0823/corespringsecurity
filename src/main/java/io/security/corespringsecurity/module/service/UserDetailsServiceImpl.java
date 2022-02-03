package io.security.corespringsecurity.module.service;

import io.security.corespringsecurity.module.domain.entity.Account;
import io.security.corespringsecurity.module.domain.entity.AccountRole;
import io.security.corespringsecurity.module.repository.AccountRepository;
import io.security.corespringsecurity.module.repository.AccountRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final AccountRoleRepository accountRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with email: " + email));

        Long accountId = account.getId();
        List<AccountRole> accountRoles = accountRoleRepository.findAllByAccountId(accountId);
        List<String> roleNames = accountRoles.stream().map(accountRole -> accountRole.getRole().getRoleName()).collect(Collectors.toList());

        List<GrantedAuthority> authorities = roleNames
                .stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        //List<GrantedAuthority> collect = userRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new AccountContext(account, authorities);
    }
}
