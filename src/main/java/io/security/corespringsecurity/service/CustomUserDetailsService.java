package io.security.corespringsecurity.service;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //jpa를 사용하여 account 테이블에서 계정 정보 조회
        Account account = userRepository.findByUsername(username);

//        if (account == null) {
//            throw new UsernameNotFoundException("UsernameNotFound");
//        }
//
//        List<GrantedAuthority> roles = new ArrayList<>();
//        roles.add(new SimpleGrantedAuthority(account.getRole())); //계정이 갖고 있는 권한을 부여한다.
//
//        AccountContext accountContext = new AccountContext(account, roles);
//
//        return accountContext;
        return null;
    }
}
