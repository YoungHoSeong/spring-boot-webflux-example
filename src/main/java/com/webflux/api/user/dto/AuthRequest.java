package com.webflux.api.user.dto;

import lombok.*;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    private String username;
    private String password;
}
