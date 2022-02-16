package com.webflux.api.user.handler;

import com.webflux.api.common.ResultError;
import com.webflux.api.security.JWTUtil;
import com.webflux.api.security.UserDetailsService;
import com.webflux.api.user.dto.AuthRequest;
import com.webflux.api.user.dto.AuthResponse;
import com.webflux.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthHandler {
    @Value("${jjwt.access.token.expiration}")
    private String accessTokenExpirationTime;

    @Value("${jjwt.refresh.token.expiration}")
    private String refreshTokenExpirationTime;

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public Mono<ServerResponse> login(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(AuthRequest.class).flatMap(user -> {

            Mono<UserDetails> userDetails = userDetailsService.findByUsername(user.getUsername());

           return userDetails.flatMap(userDetail -> {
                if (passwordEncoder.matches(user.getPassword(), userDetail.getPassword())) {
                    String accessToken = jwtUtil.generateToken(userDetail, Long.parseLong(accessTokenExpirationTime));
                    String refreshToken = jwtUtil.generateToken(userDetail, Long.parseLong(refreshTokenExpirationTime));

                    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, accessToken).body(Mono.just(new AuthResponse(accessToken, refreshToken)), AuthResponse.class);

                } else {
                    return ServerResponse.status(HttpStatus.FORBIDDEN).bodyValue(new ResultError(UsernameNotFoundException.class, "The id or password do not match.", HttpStatus.FORBIDDEN.value()));
                }
            }).onErrorResume(error -> ServerResponse.status(HttpStatus.FORBIDDEN).bodyValue(new ResultError(UsernameNotFoundException.class, "The id or password do not match.", HttpStatus.FORBIDDEN.value())));


            /*User userDetails = userRepository.findByUsername(user.getUsername());
            if(userDetails != null){
                if (passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
                    String accessToken = jwtUtil.generateToken(userDetails, Long.parseLong(accessTokenExpirationTime));
                    String refreshToken = jwtUtil.generateToken(userDetails, Long.parseLong(refreshTokenExpirationTime));

                    Authentication auth = new UsernamePasswordAuthenticationToken(accessToken, accessToken);

                    SecurityContextHolder.getContext().setAuthentication(auth);

                    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, accessToken).body(Mono.just(new AuthResponse(accessToken, refreshToken)), AuthResponse.class);

                } else {
                    return ServerResponse.status(HttpStatus.FORBIDDEN).bodyValue(new ResultError(UsernameNotFoundException.class, "The id or password do not match.", HttpStatus.FORBIDDEN.value()));
                }
            } else{
                return ServerResponse.status(HttpStatus.FORBIDDEN).bodyValue(new ResultError(UsernameNotFoundException.class, "The id or password do not match.", HttpStatus.FORBIDDEN.value()));
            }*/
        });
    }
}
