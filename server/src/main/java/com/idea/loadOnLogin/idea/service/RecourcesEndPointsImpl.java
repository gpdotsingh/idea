package com.idea.loadOnLogin.idea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecourcesEndPointsImpl implements ResourceEndPoints{

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<String> resourceOneHealth() {
        return restTemplate.getForEntity("http://resource-one-deployment:8087/actuator/health/custom", String.class);
    }
}
