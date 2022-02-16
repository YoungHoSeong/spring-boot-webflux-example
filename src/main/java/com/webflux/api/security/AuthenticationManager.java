package com.webflux.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JWTUtil jwtUtil;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();


        try {
            Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
            List<Map<String, String>> rolesMap = claims.get("role", List.class);

            List<GrantedAuthority> authorities = rolesMap.stream().map(role -> new SimpleGrantedAuthority(role.get("authority"))).collect(Collectors.toList());

            Authentication auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);

            return Mono.just(auth);

        } catch (SignatureException | ExpiredJwtException e) {
            return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e));
        }
    }
}
