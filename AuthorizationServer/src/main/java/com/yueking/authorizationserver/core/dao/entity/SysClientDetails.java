package com.yueking.authorizationserver.core.dao.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

@Data
public class SysClientDetails implements ClientDetails {
    private String clientId;
    private String clientSecret;
    private Set<String> resourceIds = new LinkedHashSet<>();
    private Set<String> scope = new LinkedHashSet<>();
    private Set<String> authorizedGrantTypes = new LinkedHashSet<>();
    private boolean autoApprove;
    private boolean secretRequired;
    private boolean scoped;

    private String registeredRedirectUri;

    private List<GrantedAuthority> authorities = new LinkedList<>();

    private int accessTokenValiditySeconds;
    private int refreshTokenValiditySeconds;


    @Override
    public boolean isAutoApprove(String scope) {
        return this.autoApprove;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
