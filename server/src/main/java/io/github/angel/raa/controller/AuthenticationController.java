package io.github.angel.raa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.angel.raa.dto.request.auth.LoginRequest;
import io.github.angel.raa.dto.request.auth.RegisterRequest;
import io.github.angel.raa.dto.response.jwt.JwtResponse;
import io.github.angel.raa.exception.WeakPasswordException;
import io.github.angel.raa.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@PreAuthorize("permitAll")
@Validated
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;
    private final CompromisedPasswordChecker compromisedPasswordChecker;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@Valid @RequestBody RegisterRequest request) {
        CompromisedPasswordDecision decision = compromisedPasswordChecker.check(request.getPassword());
        if (decision.isCompromised()) {
            log.warn("Intento de registro con contraseña comprometida para usuario: {}", request.getUsername());

            throw new WeakPasswordException(
                    "La contraseña ha sido encontrada en una brecha de datos. Por favor, elige una contraseña diferente.");

        }
        return ResponseEntity.ok(authenticationService.register(request));

    }

    @PostMapping("/login")
    ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

}
