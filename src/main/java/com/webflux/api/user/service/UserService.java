package com.webflux.api.user.service;

import com.webflux.api.user.entity.User;
import com.webflux.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Mono<User> findByUsername(String username){
        return Mono.just(userRepository.findByUsername(username));
    }
}
