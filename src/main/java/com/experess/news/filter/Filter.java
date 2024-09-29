package com.experess.news.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class Filter extends OncePerRequestFilter {

    private final List<String> PUBLIC_ENDPOINTS;

    private final List<String> PUBLIC_ENDPOINTS_METHOD;



    public Filter(@Value("${public.endpoints}") String[] PUBLIC_ENDPOINTS,
                  @Value("${public.endpoints.methods}") String[] PUBLIC_ENDPOINTS_METHOD) {
        this.PUBLIC_ENDPOINTS = Arrays.asList(PUBLIC_ENDPOINTS);
        this.PUBLIC_ENDPOINTS_METHOD = Arrays.asList(PUBLIC_ENDPOINTS_METHOD);
    }


    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        boolean isPublicEndpoint = PUBLIC_ENDPOINTS.stream().anyMatch(publicEndpoint -> requestURI.startsWith(publicEndpoint));

        boolean isPublicEndpointMethod = PUBLIC_ENDPOINTS_METHOD.stream().anyMatch(publicEndpoint -> requestURI.startsWith(publicEndpoint));
        boolean checkis = isPublicEndpointMethod && "GET".equals(method);

        if (isPublicEndpoint || checkis) {
            HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request) {
                @Override
                public String getHeader(String name) {
                    if ("Authorization".equalsIgnoreCase(name)) {
                        return "";
                    }

                    return super.getHeader(name);
                }
            };
            filterChain.doFilter(requestWrapper, response);
        }else{
            filterChain.doFilter(request, response);
        }

    }
}
