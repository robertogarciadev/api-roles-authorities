package com.example.rolesAuth.security.jwt;

import com.example.rolesAuth.provider.UrlProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Uris excludes from JWTFilter
        String uri = request.getRequestURI();
        List<String> urisToExclude = UrlProvider.urisPermitsAll();
        if (urisToExclude.contains(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication authentication = jwtProvider.getAuthentication(request);
        boolean tokenValid = jwtProvider.isTokenValid(request);

        if (tokenValid && authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }


}
