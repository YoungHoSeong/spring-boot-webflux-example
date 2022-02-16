package com.webflux.api.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<UserDetails> {
    @Override
    public Optional<UserDetails> getCurrentAuditor() {
        return Optional.empty();
    }

/*    @Override
    public Optional<UserDetails> getCurrentAuditor(){

        System.out.println("SecurityContextHolder.getContext().getAuthentication() = " + ReactiveSecurityContextHolder.getContext());


        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .filter(a -> a != null && a.isAuthenticated())
                .map(Authentication::getPrincipal)
                .cast(UserDetails.class)
                .map(auth -> auth.getUsername())
                .switchIfEmpty(Mono.empty())
                .blockOptional();

    }*/

}
