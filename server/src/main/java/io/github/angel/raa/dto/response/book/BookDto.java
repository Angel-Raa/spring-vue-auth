package io.github.angel.raa.dto.response.book;

import java.time.LocalDate;
import java.util.UUID;

public record BookDto(
                UUID bookId,
                String title,
                String author,
                String isbn,
                String slug,
                String image,
                LocalDate publicationYear,
                Boolean available) {

}
