package io.github.angel.raa.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.github.angel.raa.dto.response.book.BookDto;

public interface BookService {
    // 🔍 Consultas
    Page<BookDto> getAll(Pageable pageable);
    BookDto getByTitle(String title);
    BookDto getByIsbn(String isbn);
    Page<BookDto> getByAuthor(String author, Pageable pageable);
    Page<BookDto> getAvailableBooks(Pageable pageable);
    Page<BookDto> getBooksByOwner(String username, Pageable pageable);


    // ✏️ CRUD
    BookDto save(BookDto book, String username);
    BookDto update(UUID bookId, BookDto book, String username);
    void deleteById(UUID bookId);

}
