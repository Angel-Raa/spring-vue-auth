package io.github.angel.raa.dto.response.jwt;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.github.angel.raa.persistence.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 1182716261525128371L;
    private String token;
    private TokenType type;
    private String username;
    private Collection<? extends GrantedAuthority> roles;
    private Boolean enabled;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp;

    public enum TokenType {
        BEARER
    }

    public JwtResponse(String token) {
        this.token = token;

    }

    
}
