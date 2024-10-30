package com.experess.news.filter;

import com.experess.news.securityconfig.EndpointsConfig;
import com.experess.news.service.AuthenticationService;
import com.experess.news.securityconfig.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final EndpointsConfig endpointsConfig;


    private List<String> Public_endPoin = new ArrayList<>();

    @Autowired
    public JwtAuthFilter(@Lazy JwtService jwtService,
                        @Lazy AuthenticationService authenticationService,
                         EndpointsConfig endpointsConfig) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.endpointsConfig = endpointsConfig;
//       this.Public_endPoin = Arrays.stream(endpointsConfig.getPublicEndpoints().toArray(new String[0])).toList();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Public_endPoin = Arrays.stream(endpointsConfig.getPublicEndpoints().toArray(new String[0])).toList();
        if (Public_endPoin.stream().anyMatch(endpoint -> request.getRequestURI().startsWith(endpoint))) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);

        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = authenticationService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);

    }
}
