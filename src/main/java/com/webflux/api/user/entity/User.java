package com.webflux.api.user.entity;

import com.webflux.api.common.BaseDateTimeEntity;
import com.webflux.api.common.BaseEntity;
import com.webflux.api.user.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity implements UserDetails {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;
    private String accessToken;
    private String refreshToken;
    private String name;
    private String mobile;
    private String tel;

    public User(String username, String password, List<Role> roles, String name, String mobile, String tel) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.name = name;
        this.mobile = mobile;
        this.tel = tel;
    }

    public User(String username) {
        this.username = username;
    }

    public void changeAccessToken(String generateToken){
        this.accessToken = generateToken;
    }


    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }
}
