package io.security.corespringsecurity.module.security.filter;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PermitAllFilter extends FilterSecurityInterceptor {
    private static final String FILTER_APPLIED = "__spring_security_filterSecurityInterceptor_filterApplied";
//    private FilterInvocationSecurityMetadataSource securityMetadataSource;
    private boolean observeOncePerRequest = true;

    private List<RequestMatcher> permitAllRequestMatchers = new ArrayList<>();

    public PermitAllFilter(String...permitAllResources) {

        for (String permitAllResource : permitAllResources) {
            permitAllRequestMatchers.add(new AntPathRequestMatcher(permitAllResource));
        }
    }

    @Override
    protected InterceptorStatusToken beforeInvocation(Object object) {

        boolean permitAll = false;
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        for (RequestMatcher requestMatcher : permitAllRequestMatchers) {
            if (requestMatcher.matches(request)) {
                permitAll = true;
                break;
            }
        }

        if (permitAll) {
            return null;
        }

        //위 과정들을 모두 통과한다면 부모 클래스의 metadataSource 탐색 과정을 거치도록 한다.
        return super.beforeInvocation(object);
    }

//    public FilterSecurityInterceptor() {
//    }

//    public void init(FilterConfig arg0) {
//    }
//
//    public void destroy() {
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        FilterInvocation fi = new FilterInvocation(request, response, chain);
//        this.invoke(fi);
//    }

//    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
//        return this.securityMetadataSource;
//    }
//
//    public SecurityMetadataSource obtainSecurityMetadataSource() {
//        return this.securityMetadataSource;
//    }
//
//    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource newSource) {
//        this.securityMetadataSource = newSource;
//    }
//
//    public Class<?> getSecureObjectClass() {
//        return FilterInvocation.class;
//    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        if (fi.getRequest() != null && fi.getRequest().getAttribute("__spring_security_filterSecurityInterceptor_filterApplied") != null && this.observeOncePerRequest) {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } else {
            if (fi.getRequest() != null && this.observeOncePerRequest) {
                fi.getRequest().setAttribute("__spring_security_filterSecurityInterceptor_filterApplied", Boolean.TRUE);
            }

            InterceptorStatusToken token = beforeInvocation(fi);

            try {
                fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            } finally {
                super.finallyInvocation(token);
            }

            super.afterInvocation(token, (Object)null);
        }

    }

//    public boolean isObserveOncePerRequest() {
//        return this.observeOncePerRequest;
//    }

//    public void setObserveOncePerRequest(boolean observeOncePerRequest) {
//        this.observeOncePerRequest = observeOncePerRequest;
//    }
}
