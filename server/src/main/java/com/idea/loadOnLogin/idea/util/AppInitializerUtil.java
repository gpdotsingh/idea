package com.idea.loadOnLogin.idea.util;

import com.idea.loadOnLogin.idea.model.Oauth2SecurityDetails;
import com.idea.loadOnLogin.idea.model.Oauth2SecurityMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Order(2)
@Configuration
public class AppInitializerUtil implements InitializingBean {

    @Autowired
    Oauth2SecurityMap oauth2SecurityMap;

    Map<String, Oauth2SecurityDetails> clientRegistrations = new HashMap<>();
    @Override
    public void afterPropertiesSet() throws Exception {

        // Setting up clients
        Oauth2SecurityDetails cognito2 = new Oauth2SecurityDetails();
        cognito2.setRegistrationId("cognito2");
        cognito2.setAuthorizationUri("https://gp-test-userpool.auth.us-east-2.amazoncognito.com/oauth2/authorize");
        cognito2.setClientId("66arrjn3rp81ccq22b72fa3dvn");
        cognito2.setClientsecret("1736c9mrnfk0tobmoas46g9tovbbucf5nen1m1vc67cvkgu5titm");
        cognito2.setJwkSetUri("https://cognito-idp.us-east-2.amazonaws.com/us-east-2_72ZHiqEHa/.well-known/jwks.json");
        cognito2.setClientName("app-client1");
        cognito2.setScope(new String[]{"openid"});
        cognito2.setTokenUri("https://gp-test-userpool.auth.us-east-2.amazoncognito.com/oauth2/token");
        cognito2.setUserInfoUri("https://gp-test-userpool.auth.us-east-2.amazoncognito.com/oauth2/userInfo");
        cognito2.setAuthorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        cognito2.setRedirectUriTemplate("http://localhost:4200/callback");

        Oauth2SecurityDetails cognito1 = new Oauth2SecurityDetails();
        cognito1.setAuthorizationUri("https://gp-test-userpool.auth.us-east-2.amazoncognito.com/oauth2/authorize");
        cognito1.setClientId("360ek5g5t3fvoeqm32neo185hb");
        cognito1.setClientsecret("3chlgdirvacevb5hp4icncoo4hmqu0noedvdpahaviqmka3hlbi");
        cognito1.setJwkSetUri("https://cognito-idp.us-east-2.amazonaws.com/us-east-2_72ZHiqEHa/.well-known/jwks.json");
        cognito1.setClientName("app-client2");
        cognito1.setScope(new String[]{"openid"});
        cognito1.setTokenUri("https://gp-test-userpool.auth.us-east-2.amazoncognito.com/oauth2/token");
        cognito1.setUserInfoUri("https://gp-test-userpool.auth.us-east-2.amazoncognito.com/oauth2/userInfo");
        cognito1.setAuthorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        cognito1.setRedirectUriTemplate("http://localhost:4200/callback");

        clientRegistrations.put("cognito1",cognito1);
        clientRegistrations.put("cognito2",cognito2);

        oauth2SecurityMap.setClientRegistrations(clientRegistrations);

    }

}
