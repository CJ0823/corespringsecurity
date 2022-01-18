package io.security.corespringsecurity.security.provider;

import io.security.corespringsecurity.security.service.AccountContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //설계나 정책에 따라서 인증 메서드를 다양하게 구현할 수 있다. 아래는 가장 기본적인 예시이다.

        String username = authentication.getName();
        String password = (String) authentication.getCredentials(); //Object 타입으로 반환되어 캐스팅

        AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(username); //직접 만든 클래스로 캐스팅

        if(!passwordEncoder.matches(password, accountContext.getAccount().getPassword())) {
            throw new BadCredentialsException("BadCredentialsException");
        }

        //인증 성공 시 성공한 인증 객체를 생성 후 반환
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(accountContext.getAccount(), null, accountContext.getAuthorities());

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //전달된 파라미터의 타입이 UsernamePasswordAuthenticationToken 타입과 일치하는지 검사한다.
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
