package io.security.corespringsecurity.module.security.configs;

import io.security.corespringsecurity.module.security.common.AjaxLoginAuthenticationEntryPoint;
import io.security.corespringsecurity.module.security.handler.AjaxAccessDeniedHandler;
import io.security.corespringsecurity.module.security.handler.AjaxAuthenticationFailureHandler;
import io.security.corespringsecurity.module.security.handler.AjaxAuthenticationSuccessHandler;
import io.security.corespringsecurity.module.security.provider.AjaxAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

//@Configuration
//@Order(0)
public class AjaxSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(ajaxAuthenticationProvider());
//    }
//
//    @Bean
//    public AuthenticationProvider ajaxAuthenticationProvider() {
//        return new AjaxAuthenticationProvider();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
//        return new AjaxAuthenticationSuccessHandler();
//    }
//
//    @Bean
//    public AuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
//        return new AjaxAuthenticationFailureHandler();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .antMatcher("/api/**")
//                .authorizeRequests()
//                .antMatchers("/api/messages").hasAuthority("MANAGER")
//                .anyRequest().authenticated()
////                .and()
////                .addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class) //기존 필터 앞에 위치할때 사용하는 method
//        ;
//
//        http
//                .exceptionHandling()
//                .authenticationEntryPoint(new AjaxLoginAuthenticationEntryPoint())
//                .accessDeniedHandler(new AjaxAccessDeniedHandler())
//                ;
//
//        http.csrf().disable(); //기본적으로 csrf 토큰값을 들고 요청을 해야되는데, 임시적으로 off 한다.
//
//        customConfigurerAjax(http);
//    }
//
//    private void customConfigurerAjax(HttpSecurity http) throws Exception {
//        http
//                .apply(new AjaxLoginConfigurer<>())
//                .successHandlerAjax(ajaxAuthenticationSuccessHandler())
//                .failureHandlerAjax(ajaxAuthenticationFailureHandler())
//                .setAuthenticationManager(authenticationManagerBean())
//                .loginProcessingUrl("/api/login")
//        ;
//    }
//
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }


//    @Bean
//    public AjaxLoginProcessingFilter ajaxLoginProcessingFilter() throws Exception {
//        AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter();
//        ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManagerBean());
//        //인증 성공, 실패 시 handler 처리
//        ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler());
//        ajaxLoginProcessingFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler());
//        return ajaxLoginProcessingFilter;
//    }
}
