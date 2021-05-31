package com.idea.loadOnLogin.idea.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
public class OAuth2LoginConfig {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.clientRegistration());
    }

    private ClientRegistration[] clientRegistration() {
        ClientRegistration [] crArray = new ClientRegistration[2];

        ClientRegistration cr1 =
                ClientRegistration.withRegistrationId("cognito1")
                        .clientName("app-client1")
                        .clientId("66arrjn3rp81ccq22b72fa3dvn")
                        .clientSecret("1736c9mrnfk0tobmoas46g9tovbbucf5nen1m1vc67cvkgu5titm")
                        .scope(new String[]{"openid"})
                        .authorizationUri("https://gp-test-userpool.auth.us-east-2.amazoncognito.com/oauth2/authorize")
                        .tokenUri("https://gp-test-userpool.auth.us-east-2.amazoncognito.com/oauth2/token")
                        .userInfoUri("https://gp-test-userpool.auth.us-east-2.amazoncognito.com/oauth2/userInfo")
                        .jwkSetUri("https://cognito-idp.us-east-2.amazonaws.com/us-east-2_72ZHiqEHa/.well-known/jwks.json")
                        .clientName("app-client")
                        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                        .redirectUri("http://localhost:4200/callback")
                        .build();

        ClientRegistration cr2 =
                ClientRegistration.withRegistrationId("cognito2")
                        .clientName("app-client2")
                        .clientId("360ek5g5t3fvoeqm32neo185hb")
                        .clientSecret("3chlgdirvacevb5hp4icncoo4hmqu0noedvdpahaviqmka3hlbi")
                        .scope(new String[]{"openid"})
                        .authorizationUri("https://gp-test-userpool.auth.us-east-2.amazoncognito.com/oauth2/authorize")
                        .tokenUri("https://gp-test-userpool.auth.us-east-2.amazoncognito.com/oauth2/token")
                        .userInfoUri("https://gp-test-userpool.auth.us-east-2.amazoncognito.com/oauth2/userInfo")
                        .jwkSetUri("https://cognito-idp.us-east-2.amazonaws.com/us-east-2_72ZHiqEHa/.well-known/jwks.json")
                        .clientName("app-client2")
                        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                        .redirectUri("http://localhost:4200/callback")
                        .build();

        crArray[0] = cr1;
        crArray[1] = cr2;
        return crArray;
    }

}
