package io.github.angel.raa.dto.response.book;

import java.time.LocalDate;
import java.util.UUID;

import io.github.angel.raa.dto.response.user.UserDto;

public record BookWithOwnerDto(
        UUID bookId,
        String title,
        String author,
        String isbn,
        String slug,
        LocalDate publicationYear,
        Boolean available,
        UserDto owner // 👈 Nuevo campo
) {

}
