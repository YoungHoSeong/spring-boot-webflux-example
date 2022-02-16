package com.webflux.api.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {
    private Long id;
    private String username;
    private String name;
    private String mobile;
}
