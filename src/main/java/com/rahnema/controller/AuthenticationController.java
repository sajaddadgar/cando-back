package com.rahnema.controller;

import com.rahnema.domain.AuthenticationRequest;
import com.rahnema.domain.AuthenticationResponse;
import com.rahnema.service.UserDetailsServiceImpl;
import com.rahnema.util.JwtToken;
import com.rahnema.util.JwtTokenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtToken jwtToken;
    private UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenImpl jwtToken, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtToken = jwtToken;
        this.userDetailsService = userDetailsService;
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse createToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtToken.generateToken(userDetails);
        return new AuthenticationResponse(token);
    }

}
