package com.stefura.mentorsplatform.security;

import com.stefura.mentorsplatform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final static String AUTHORIZATION_HEADER = "Authorization";
    private final static String JWT_TOKEN_PREFIX = "Bearer ";
    private UserService userService;
    private JwtProvider jwtProvider;

    @Autowired
    public JwtRequestFilter(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader(AUTHORIZATION_HEADER);

        String login = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith(JWT_TOKEN_PREFIX)) {
            jwt = authorizationHeader.substring(JWT_TOKEN_PREFIX.length());
            login = jwtProvider.extractUsername(jwt);
        }

        if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(login);
            if (jwtProvider.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
