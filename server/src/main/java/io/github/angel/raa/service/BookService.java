package io.github.angel.raa.service;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

import io.github.angel.raa.dto.PageDto;
import io.github.angel.raa.dto.request.book.BookRequest;
import io.github.angel.raa.dto.response.book.BookDto;

public interface BookService {
    // 🔍 Consultas
    PageDto<BookDto> getAll(Pageable pageable);

    BookDto getByTitle(String title);

    BookDto getBySlug(String slug);

    BookDto getByIsbn(String isbn);

    PageDto<BookDto> getByAuthor(String author, Pageable pageable);

    PageDto<BookDto> getAvailableBooks(Pageable pageable);

    PageDto<BookDto> getBooksByOwner(String username, Pageable pageable);

    String saveAll(List<BookRequest> books, String username);

    // ✏️ CRUD
    BookDto save(BookRequest book, String username);

    BookDto update(UUID bookId, BookRequest book, String username);

    void deleteById(UUID bookId);

}
