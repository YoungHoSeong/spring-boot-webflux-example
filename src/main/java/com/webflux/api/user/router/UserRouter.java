package com.webflux.api.user.router;

import com.webflux.api.user.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> user(UserHandler handler) {

        return RouterFunctions
                .nest(path("/api"),
                        route(GET("/users"), handler::findAllUsers)
                        .andRoute(GET("/users/{id}"), handler::findUserById)
                        .andRoute(POST("/users"), handler::saveUser)
                );
    }

}
