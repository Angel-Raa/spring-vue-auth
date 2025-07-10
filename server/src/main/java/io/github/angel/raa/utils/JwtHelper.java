package io.github.angel.raa.utils;

import org.springframework.stereotype.Component;

import io.github.angel.raa.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtHelper {
    private final JwtUtils jwtUtils;

    public String extractUsername(HttpServletRequest request) {
        String token = jwtUtils.resolveToken(request);

        if (!jwtUtils.validateToken(token)) {
            throw new UnauthorizedException("Token inválido");
        }
        if (token == null || token.isBlank()) {
            throw new UnauthorizedException("No se encontró token en el encabezado Authorization");
        }
        return jwtUtils.getUsernameFromToken(token);

    }

    

}
