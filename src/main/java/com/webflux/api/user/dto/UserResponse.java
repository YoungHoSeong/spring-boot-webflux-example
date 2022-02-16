package com.webflux.api.user.dto;

import com.webflux.api.user.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private List<Role> authorities;
    private String name;
    private String mobile;
    private String tel;
    private String createdBy;
    private LocalDateTime createdDateTime;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDateTime;

    public UserResponse(Long id, String username, List<Role> authorities, String name, String mobile, String tel, String createdBy, LocalDateTime createdDateTime, String lastModifiedBy, LocalDateTime lastModifiedDateTime) {
        this.id = id;
        this.username = username;
        this.authorities = authorities;
        this.name = name;
        this.mobile = mobile;
        this.tel = tel;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.createdDateTime = createdDateTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public UserResponse(String username, List<Role> authorities, String name, String mobile, String tel, String createBy, LocalDateTime createdDateTime, String lastModifiedBy, LocalDateTime lastModifiedDateTime) {
        this.username = username;
        this.authorities = authorities;
        this.name = name;
        this.mobile = mobile;
        this.tel = tel;
    }
}
