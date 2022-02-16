package com.webflux.api.security;

import com.webflux.api.user.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditConfig {
    @Bean(name = "auditorProvider")
    public AuditorAware<UserDetails> auditorProvider(){
        return new AuditorAwareImpl();
    }
}
