package com.webflux.api;

import com.webflux.api.security.AuthenticationManager;
import com.webflux.api.user.Role;
import com.webflux.api.user.entity.User;
import com.webflux.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class WebfluxApplication {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return (args) -> {
            String password = passwordEncoder.encode("1234");
            userRepository.save(new User("admin", password, Arrays.asList(Role.ROLE_ADMIN, Role.ROLE_MANAGER, Role.ROLE_USER), "관리자", "010-1111-2222", "02-1111-2222"));
            userRepository.save(new User("manager", password, Arrays.asList(Role.ROLE_MANAGER, Role.ROLE_USER), "매니저", "010-2222-2222", "02-2222-2222"));
            userRepository.save(new User("user", password, Arrays.asList(Role.ROLE_USER), "사용자", "010-2222-2222", "02-2222-3333"));
        };

    }

   /* @Bean
    public AuditorAware<String> auditorProvider(){

        return () -> {
            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                System.out.println(":::::::::::::::::::::::::    "+SecurityContextHolder.getContext().getAuthentication().getName());
                SecurityContext context = SecurityContextHolder.getContext();
                System.out.println("isAuthenticated = " + context.getAuthentication().isAuthenticated());
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                return Optional.of(authentication.getName());
            } else {
//                System.out.println(":::::::::::::::::::::::::    "+SecurityContextHolder.getContext().getAuthentication().getName());
                return Optional.of("Unknown");
            }
        };
    }*/

    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
    }

}

