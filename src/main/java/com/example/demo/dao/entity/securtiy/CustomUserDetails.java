package com.example.demo.dao.entity.securtiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

    SecurityUserEntity userEntity;

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userEntity.getRole().getName()));//example
    }
}
