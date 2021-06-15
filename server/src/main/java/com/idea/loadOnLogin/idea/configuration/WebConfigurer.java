package com.idea.loadOnLogin.idea.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idea.loadOnLogin.idea.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableWebSecurity
public class WebConfigurer extends WebSecurityConfigurerAdapter {

    private final ObjectMapper mapper;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    public WebConfigurer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //@formatter:off
        super.configure(web);
        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());

    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowSemicolon(true);
        return firewall;
    }

    //Add cors , as spring security is getting used
    //hence the cors need to be declared here as well else with application.properties it won't work**
    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http
                .csrf().disable()
                .cors()
               .and()
                .authorizeRequests()
                .antMatchers("/oauth2/**","/login**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
             //   .sessionManagement()
             //  .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // started putting jsession id and also stop header gave error from origin 'http://localhost:4200' has been blocked by CORS policy: Response to preflight request doesn't pass access control check:
             //  .and() // to over come above problem add cors remove disable and header in cors configuration
                .oauth2Login()
                .successHandler(this::successHandler)

                .defaultSuccessUrl("/",true);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


    }

    private void successHandler(HttpServletRequest request,
                                HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken oauthToken =
                (OAuth2AuthenticationToken) authentication;
        System.out.println("oauthToken:::" + oauthToken);
        response.getWriter().write(
                mapper.writeValueAsString(Collections.singletonMap("token", authentication.getAuthorities()))
        );
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin","*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
