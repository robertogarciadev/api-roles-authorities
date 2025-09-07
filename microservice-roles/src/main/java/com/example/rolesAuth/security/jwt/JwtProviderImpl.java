package com.example.rolesAuth.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.rolesAuth.security.CustomUserPrincipal;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtProviderImpl implements JwtProvider {

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    @Value("${app.jwt.expiration-in-ms}")
    private Long JWT_EXPIRATION;

    private SecretKey secretKey;

    @PostConstruct
    void init() {
        byte[] keyBytes = this.SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public boolean isTokenValid(HttpServletRequest request) {
        Claims claims = extractClaims(request);
        return claims != null && claims.getExpiration().after(new Date());
    }

    @Override
    public String generateToken(CustomUserPrincipal user) {

        String subject = user.getMail();
        long id = user.getId();
        String autorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + this.JWT_EXPIRATION);

        return Jwts.builder()
                .subject(subject)
                .issuedAt(currentDate)
                .expiration(expirationDate)
                .claim("authorities", autorities)
                .claim("userId", id)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {

        Claims claims = extractClaims(request);

        if (claims == null) {
            return null;
        }
        String mail = claims.getSubject();
        long id = claims.get("userId", Long.class);
        String[] authoritiesString = claims.get("authorities").toString().split(",");
        Set<GrantedAuthority> grantedAuthorities = Arrays.stream(authoritiesString)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        UserDetails customUserPrincipal = CustomUserPrincipal.builder()
                .mail(mail)
                .id(id)
                .authorities(grantedAuthorities).build();

        return new UsernamePasswordAuthenticationToken(customUserPrincipal, null, grantedAuthorities);
    }

    /**
     * Extact Claims from token
     * 
     * @param request
     * @return Claims
     */
    private Claims extractClaims(HttpServletRequest request) {

        String token = extractTokenFromRequest(request);

        if (token == null) {
            return null;
        }

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Extact token from request client
     * 
     * @param request
     * @return Token
     */
    private String extractTokenFromRequest(HttpServletRequest request) {

        final String HEADER = "authorization";
        final String PREFIX_TOKEN = "Bearer ";
        String token = request.getHeader(HEADER);

        if (StringUtils.hasLength(token) && token.startsWith(PREFIX_TOKEN)) {
            return token.substring(PREFIX_TOKEN.length());
        }
        return null;

    }
}
