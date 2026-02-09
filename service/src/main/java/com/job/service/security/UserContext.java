package com.job.service.security;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@RequiredArgsConstructor
@Data
public class UserContext {

    private final String username;
    private final Set<String> roles;

    public boolean hasRole(String role){
        return roles.contains(role);
    }
}
