package com.idea.loadOnLogin.idea.util;

import com.idea.loadOnLogin.idea.model.Oauth2SecurityDetails;
import com.idea.loadOnLogin.idea.model.Token;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

public class AccessTokenUtil {

    public static String getBasicAuthHeader(String clientId,String clientSecret){
        return Base64.getEncoder().encodeToString((clientId+":"
                + clientSecret).getBytes());

    }

    public static ResponseEntity<Token> getToken(String uri, String state, String code, String basicAuthHeader,
                                                 Oauth2SecurityDetails oauth2SecurityDetails) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
        headers.add("Authorization", "Basic "+basicAuthHeader); //Optional in case server sends back JSON data

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
        requestBody.add("state", state);
        requestBody.add("code", code);
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("redirect_uri", oauth2SecurityDetails.getRedirectUriTemplate());

        HttpEntity formEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);

        ResponseEntity<Token> response =
                restTemplate.exchange(uri, HttpMethod.POST,
                        formEntity, Token.class);

        return ResponseEntity.accepted().body(response.getBody());
    }

}
