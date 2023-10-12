package org.optionfactory;

import org.junit.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class WebAppInitializerTest {
    @Test
    public void defaultPasswordEncoder() {
        final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        final String encodedPassword = passwordEncoder.encode("password");
        System.out.println(encodedPassword);
    }

    @Test
    public void customPasswordEncoder() {
        final Map<String, PasswordEncoder> encoders = new HashMap<>();
        final String encoderID = "pbkdf2";
        encoders.put(encoderID, Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());

        final PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(encoderID, encoders);
        final String encodedPassword = passwordEncoder.encode("password");
        System.out.println(encodedPassword);
    }
    
    @Test
    public void setSecurityContext() {
        final SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        final Authentication authenticationProvided = new TestingAuthenticationToken("username", "password", "ROLE_USER");
        securityContext.setAuthentication(authenticationProvided);
        SecurityContextHolder.setContext(securityContext);

        final Authentication authenticationRetrieved = securityContext.getAuthentication();
        final String username = authenticationRetrieved.getName();
        final Object principal = authenticationRetrieved.getCredentials();
        final Collection<? extends GrantedAuthority> authorities = authenticationRetrieved.getAuthorities();

        System.out.println(username);
        System.out.println(principal);
        System.out.println(authorities);
    }
}