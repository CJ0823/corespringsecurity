package io.security.corespringsecurity.module.security.common;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxLoginAuthenticationEntryPoint implements AuthenticationEntryPoint {

    //익명 사용자가 인증이 필요한 자원에 접근한 경우, commence 메서드가 호출됨
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UnAuthorized");
    }
}
