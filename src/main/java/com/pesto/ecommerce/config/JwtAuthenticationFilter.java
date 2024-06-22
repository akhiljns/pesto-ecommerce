package com.pesto.ecommerce.config;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pesto.ecommerce.constants.JwtConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter() {
        this.authenticationManager = new AuthenticationManager() {

            @Override
            public Authentication authenticate(Authentication arg0) throws AuthenticationException {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
            }
            
        };
        setFilterProcessesUrl("/login"); // Set the endpoint for login
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Parse user credentials from the request
            UserCredentials userCredentials = new ObjectMapper().readValue(request.getInputStream(),
                    UserCredentials.class);

            // Create an authentication token
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userCredentials.getUsername(),
                    userCredentials.getPassword());

            // Authenticate the user
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        // Generate JWT token upon successful authentication
        String token = Jwts.builder()
                .setSubject(((User) authResult.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, JwtConstants.SECRET)
                .compact();

        // Add the token to the response header
        response.addHeader(JwtConstants.HEADER_STRING, JwtConstants.TOKEN_PREFIX + token);
    }

    @Getter
    @Setter
    private static class UserCredentials {
        private String username;
        private String password;
    }
}
