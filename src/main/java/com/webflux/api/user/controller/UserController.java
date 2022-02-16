package com.webflux.api.user.controller;

import com.webflux.api.user.dto.UserResponse;
import com.webflux.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

//    @GetMapping(value = "/api/username")
//    public Mono<UserResponse> findUsername(String username){
//        return userService.findByUsername(username).map(user -> new UserResponse(user.getId(), user.getUsername(), user.getAuthorities(), user.getName(), user.getMobile(), user.getTel(), user.getCreateBy(), user.getCreatedDateTime(), user.getLastModifiedBy(), user.getLastModifiedDateTime()));
//    }
}
