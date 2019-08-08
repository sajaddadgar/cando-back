package com.rahnema.configuration;

import com.rahnema.exception.WrongArgumantException;
import com.rahnema.service.UserDetailsServiceImpl;
import com.rahnema.util.JwtToken;
import com.rahnema.util.JwtTokenImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;
    private JwtToken jwtToken;

    @Autowired
    public AuthenticationFilter(UserDetailsServiceImpl userDetailsService, JwtTokenImpl jwtToken) {
        this.userDetailsService = userDetailsService;
        this.jwtToken = jwtToken;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String email = null;
        String token = null;

        final String requestTokenHeader = request.getHeader("Authorization");
        // get token

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            token = requestTokenHeader.substring(7);
            try {
                email = jwtToken.getEmailFromToken(token);
            } catch (WrongArgumantException e) {
                System.out.println("unable to get jwt token");
                // Todo: add ExpireJwtException in exception
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }

        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }


        // validate token
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
            if (jwtToken.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
