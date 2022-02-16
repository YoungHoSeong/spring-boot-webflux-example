package com.webflux.api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.security.sasl.AuthenticationException;

@Component
@RequiredArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository{
    private final AuthenticationManager authenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();

        if (request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION) != null) {

            String accessToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            Authentication auth = new UsernamePasswordAuthenticationToken(accessToken, accessToken);

            SecurityContextHolder.getContext().setAuthentication(auth);

            return authenticationManager.authenticate(auth).map((authentication) -> new SecurityContextImpl(authentication));
        } else {
            return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "There is no authorization authority", new AuthenticationException()));
        }
    }
}
