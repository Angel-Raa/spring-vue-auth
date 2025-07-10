package io.github.angel.raa.service;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

import io.github.angel.raa.dto.PageDto;
import io.github.angel.raa.dto.response.book.BookDto;

public interface BookService {
    // 🔍 Consultas
    PageDto<BookDto> getAll(Pageable pageable);
    BookDto getByTitle(String title);
    BookDto getByIsbn(String isbn);
    PageDto<BookDto> getByAuthor(String author, Pageable pageable);
    PageDto<BookDto> getAvailableBooks(Pageable pageable);
    PageDto<BookDto> getBooksByOwner(String username, Pageable pageable);
    String saveAll(List<BookDto> books, String username);

    // ✏️ CRUD
    BookDto save(BookDto book, String username);
    BookDto update(UUID bookId, BookDto book, String username);
    void deleteById(UUID bookId);


}
