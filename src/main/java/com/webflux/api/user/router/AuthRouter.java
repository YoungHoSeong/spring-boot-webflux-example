package com.webflux.api.user.router;

import com.webflux.api.user.handler.AuthHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AuthRouter {

    @Bean
    public RouterFunction<ServerResponse> auth(AuthHandler handler) {
        return route(POST("/login").and(accept(APPLICATION_JSON)), handler::login);
    }
}
