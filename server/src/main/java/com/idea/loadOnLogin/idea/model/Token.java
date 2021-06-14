package com.idea.loadOnLogin.idea.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String id_token;
    private String access_token;
    private String refresh_token;
    private float expires_in;
    private String token_type;
}
