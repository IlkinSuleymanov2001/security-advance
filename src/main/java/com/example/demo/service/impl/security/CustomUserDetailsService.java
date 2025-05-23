package com.example.demo.service.impl.security;

import com.example.demo.dao.entity.securtiy.CustomUserDetails;
import com.example.demo.dao.repository.security.UserSecurityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomUserDetailsService implements UserDetailsService {

    UserSecurityRepository userSecurityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userEntity = userSecurityRepository.findByEmail(username)
                .orElseThrow(()
                        -> new RuntimeException("User Not Found"));

        return new CustomUserDetails(userEntity);
    }
}
