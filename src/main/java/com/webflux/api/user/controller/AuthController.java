package com.webflux.api.user.controller;

import com.webflux.api.security.JWTUtil;
import com.webflux.api.user.dto.AuthRequest;
import com.webflux.api.user.dto.AuthResponse;
import com.webflux.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthController {
    @Value("${jjwt.access.token.expiration}")
    private String accessTokenExpirationTime;

    @Value("${jjwt.refresh.token.expiration}")
    private String refreshTokenExpirationTime;

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JWTUtil jwtUtil;


//    @RequestMapping(value = "/login", method = RequestMethod.POST)
 /*   public Mono<ResponseEntity<?>> login(AuthRequest authRequest, ServerHttpResponse response) {

        return userService.findByUsername(authRequest.getUsername()).map((userDetails) -> {
            if (passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())) {
                String accessToken = jwtUtil.generateToken(userDetails, Long.parseLong(accessTokenExpirationTime));
                String refreshToken = jwtUtil.generateToken(userDetails, Long.parseLong(refreshTokenExpirationTime));

                response.getHeaders().add(HttpHeaders.AUTHORIZATION, accessToken);
                return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Mono.error(new UsernameNotFoundException("The id or password do not match.")));
            }
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Mono.error(new UsernameNotFoundException("The id or password do not match."))));
    }*/


}
