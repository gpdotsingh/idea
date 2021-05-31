package com.idea.loadOnLogin.idea.controller;

import com.idea.loadOnLogin.idea.model.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@RestController
public class HomeController {
    @RequestMapping(value = "/oAuth2AuthenticationToken",method = RequestMethod.GET)
    public void getAccessToken(HttpServletRequest request, HttpServletResponse response, OAuth2AuthenticationToken authentication){
         System.out.println(authentication);
         System.out.println("Debug karo");
    }

    @RequestMapping(value = "/authenticationPrincipal",method = RequestMethod.GET)
    public ResponseEntity<Token> getAccessToken(@AuthenticationPrincipal OidcUser authentication){

        System.out.println(authentication);
        System.out.println("Debug karo");

        return new ResponseEntity<>(new Token(authentication.getIdToken().getTokenValue()), HttpStatus.ACCEPTED);
    }
}
