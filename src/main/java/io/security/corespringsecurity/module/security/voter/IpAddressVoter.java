package io.security.corespringsecurity.module.security.voter;

import io.security.corespringsecurity.module.service.impl.SecurityResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class IpAddressVoter implements AccessDecisionVoter {

    private final SecurityResourceService securityResourceService;

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object o, Collection collection) {

        //authentication.getDetails에서 사용자 정보를 얻을 수 있으며, WebAuthenticationDetails 객체로 캐스팅 해줘야 다양한 메서드 사용 가능
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String remoteAddress = details.getRemoteAddress();

        List<String> accessIpList = securityResourceService.getAccessIpList();

        for (String accessIp : accessIpList) {
            if (accessIp.equals(remoteAddress)) {
                return ACCESS_ABSTAIN;
            }
        }

        throw new AccessDeniedException("Invalid Ip Address");

    }
}
