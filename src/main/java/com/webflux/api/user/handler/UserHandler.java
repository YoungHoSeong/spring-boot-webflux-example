package com.webflux.api.user.handler;

import com.webflux.api.common.ResponseEntity;
import com.webflux.api.user.Role;
import com.webflux.api.user.dto.UserResponse;
import com.webflux.api.user.entity.User;
import com.webflux.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserHandler {
    private final UserRepository userRepository;

    public Mono<ServerResponse> findAllUsers(ServerRequest serverRequest) {

        Page<User> userPage = userRepository.findAll(PageRequest.of(0, 10));
        ReactiveSecurityContextHolder.getContext().flatMap(securityContext -> {
            System.out.println("securityContext = " + securityContext.getAuthentication().getPrincipal());
            return null;
        });


        List<UserResponse> users =
                userPage.getContent().stream().map(
                        user -> new UserResponse(user.getId(), user.getUsername(), user.getRoles(), user.getName(), user.getMobile(), user.getTel(), user.getCreateBy(), user.getCreatedDateTime(), user.getLastModifiedBy(), user.getLastModifiedDateTime())
                ).collect(Collectors.toList());
        ResponseEntity responseEntity = new ResponseEntity(users, userPage.getTotalElements(), userPage.getTotalPages(), userPage.getNumber(), userPage.getPageable());

        return ServerResponse.ok().body(Mono.just(responseEntity), ResponseEntity.class);
    }

    public Mono<ServerResponse> findUserById(ServerRequest serverRequest) {
        Long id = Long.parseLong(serverRequest.pathVariable("id"));
        Optional<User> findUser = userRepository.findById(id);
        User user = findUser.orElseThrow(() -> new UsernameNotFoundException("User not Found"));

        return ServerResponse.ok().body(Mono.just(new UserResponse(user.getId(), user.getUsername(), user.getRoles(), user.getName(), user.getMobile(), user.getTel(), user.getCreateBy(), user.getCreatedDateTime(), user.getLastModifiedBy(), user.getLastModifiedDateTime())), UserResponse.class);
    }

    public Mono<ServerResponse> saveUser(ServerRequest serverRequest){
        User user = new User("user1", "dddd", Arrays.asList(Role.ROLE_USER), "사용자1", "010-1234-1234", "02-1111-1111");

        userRepository.save(user);

        return ServerResponse.ok().body(Mono.just(new UserResponse(user.getId(), user.getUsername(), user.getRoles(), user.getName(), user.getMobile(), user.getTel(), user.getCreateBy(), user.getCreatedDateTime(), user.getLastModifiedBy(), user.getLastModifiedDateTime())), UserResponse.class);
    }

}
