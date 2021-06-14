package com.idea.loadOnLogin.idea.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
public class Oauth2SecurityMap {
    private Map<String, Oauth2SecurityDetails> clientRegistrations;
}
