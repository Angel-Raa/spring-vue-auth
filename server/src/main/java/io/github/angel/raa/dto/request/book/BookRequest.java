package io.github.angel.raa.dto.request.book;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookRequest implements Serializable {
    private static final long serialVersionUID = 121323548391384337L;
    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 100, message = "El título no puede tener más de 100 caracteres")
    private String title;
    @NotBlank(message = "El autor no puede estar vacío")
    private String author;
    @NotBlank(message = "El ISBN no puede estar vacío")
    @Pattern(regexp = "^[0-9\\-]{10,13}$", message = "El ISBN debe tener entre 10 y 13 dígitos o guiones")
    @Size(max = 13, message = "El ISBN no puede tener más de 13 caracteres")
    private String isbn;
    @NotNull(message = "El año de publicación no puede estar vacío")
    @PastOrPresent(message = "El año de publicación no puede ser futuro")
    private LocalDate publicationYear;
    @NotNull(message = "El estado no puede estar vacío")
    private Boolean available;
    private String image;

}
