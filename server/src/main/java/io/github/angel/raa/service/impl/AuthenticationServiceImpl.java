package io.github.angel.raa.service.impl;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.angel.raa.dto.request.auth.LoginRequest;
import io.github.angel.raa.dto.request.auth.RegisterRequest;
import io.github.angel.raa.dto.response.jwt.JwtResponse;
import io.github.angel.raa.exception.DuplicateUsernameException;
import io.github.angel.raa.persistence.entity.Role;
import io.github.angel.raa.persistence.entity.User;
import io.github.angel.raa.persistence.repository.UserRepository;
import io.github.angel.raa.service.AuthenticationService;
import io.github.angel.raa.utils.JwtUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtils jwtUtils;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public JwtResponse login(LoginRequest request) {
        final String username = request.getUsername();
        final String password = request.getPassword();
        if (!repository.findByUsername(username).isPresent())
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        if (!passwordEncoder.matches(password, repository.findByUsername(username).get().getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        UserDetailsImpl details = (UserDetailsImpl) userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtils.generateToken(details);
        return JwtResponse.builder()
                .token(token)
                .type(JwtResponse.TokenType.BEARER)
                .username(details.getUsername())
                .roles(details.getAuthorities())
                .enabled(details.getEnabled())
                .timestamp(java.time.LocalDateTime.now())
                .build();

    }

    @Transactional
    @Override
    public JwtResponse register(RegisterRequest request) {
        final String username = request.getUsername();
        final String password = request.getPassword();
        if (repository.existsByUsername(username)) {
            throw new DuplicateUsernameException("Username already exists");
        }

        User user = buildUser(username, password, Role.USER);
        repository.save(user);
        UserDetailsImpl detailsImpl = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
         
        String token = jwtUtils.generateToken(detailsImpl);
        return JwtResponse.builder()
                .token(token)
                .type(JwtResponse.TokenType.BEARER)
                .username(detailsImpl.getUsername())
                .roles(detailsImpl.getAuthorities())
                .enabled(detailsImpl.isEnabled())
                .timestamp(java.time.LocalDateTime.now())

                .build();

    }

    private User buildUser(String username, String password, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        user.setRoles(Role.USER);
        return user;

    }

}
