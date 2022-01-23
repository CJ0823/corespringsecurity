package io.security.corespringsecurity.security.provider;

import io.security.corespringsecurity.security.service.AccountContext;
import io.security.corespringsecurity.security.token.AjaxAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AjaxAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //기본 생성자를 넣어주기 위해 @RequiredArgsConstructor 제외, @Autowired 적용

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //설계나 정책에 따라서 인증 메서드를 다양하게 구현할 수 있다. 아래는 가장 기본적인 예시이다.

        String username = authentication.getName();
        String password = (String) authentication.getCredentials(); //Object 타입으로 반환되어 캐스팅

        AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(username); //직접 만든 클래스로 캐스팅

        if(!passwordEncoder.matches(password, accountContext.getAccount().getPassword())) {
            throw new BadCredentialsException("invalid password");
        }

        //인증 성공 시 성공한 인증 객체를 생성 후 반환
        return new AjaxAuthenticationToken(accountContext.getAccount(), null, accountContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //전달된 파라미터의 타입이 AjaxAuthenticationToken 타입과 일치하는지 검사한다.
        return authentication.isAssignableFrom(AjaxAuthenticationToken.class);
    }
}
