package com.idea.loadOnLogin.idea.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.Set;

@Setter
@Getter
public class Oauth2SecurityDetails {

    private String registrationId;
    private String clientId;
    private String clientsecret;
    private String clientName;
    private AuthorizationGrantType authorizationGrantType;
    private String[] scope;
    private String redirectUriTemplate;
    private String authorizationUri;
    private String tokenUri;
    private String jwkSetUri;
    private String userInfoUri;

}