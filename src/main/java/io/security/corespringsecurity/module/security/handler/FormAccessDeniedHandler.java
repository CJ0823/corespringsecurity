package io.security.corespringsecurity.module.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class FormAccessDeniedHandler implements AccessDeniedHandler {

    private String errorPage;


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        String deniedUrl = errorPage + "?exception=" + URLEncoder.encode(e.getMessage(), "UTF-8");
        response.sendRedirect(deniedUrl);
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }
}
