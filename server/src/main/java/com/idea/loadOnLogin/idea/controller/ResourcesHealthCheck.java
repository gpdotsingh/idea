package com.idea.loadOnLogin.idea.controller;

import com.idea.loadOnLogin.idea.service.ResourceEndPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourcesHealthCheck {
@Autowired
    ResourceEndPoints  resourceEndPoints;

@GetMapping("resourceOne/health")
    public ResponseEntity<String> resourceOneHealth(){
    return resourceEndPoints.resourceOneHealth();
}
}
