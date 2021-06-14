package com.idea.loadOnLogin.idea.controller;

import com.idea.loadOnLogin.idea.model.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetAccessToken {

    @GetMapping(value = "/oauth2/getAccessToken/{registrationId}")
    public ResponseEntity<Token> getToken( @RequestParam String code, @RequestParam String state,@PathVariable String registrationId){

        return new ResponseEntity<>(new Token(), HttpStatus.ACCEPTED);
    }

}
