package io.github.angel.raa.dto.response.user;

import java.util.UUID;

public record UserDto(
        UUID userId,
        String username,
        Boolean enabled) {

}
