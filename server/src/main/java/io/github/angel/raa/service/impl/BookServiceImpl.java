package io.github.angel.raa.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.angel.raa.dto.PageDto;
import io.github.angel.raa.dto.request.book.BookRequest;
import io.github.angel.raa.dto.response.book.BookDto;
import io.github.angel.raa.exception.BookNotFoundException;
import io.github.angel.raa.mapper.BookMapper;
import io.github.angel.raa.persistence.entity.Book;
import io.github.angel.raa.persistence.entity.User;
import io.github.angel.raa.persistence.repository.BookRepository;
import io.github.angel.raa.persistence.repository.UserRepository;
import io.github.angel.raa.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final UserRepository userRepository;
    private final BookMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public PageDto<BookDto> getAll(Pageable pageable) {
        Page<Book> books = repository.findAll(pageable);
        return new PageDto<BookDto>(
                books.getContent().stream().map(mapper::toDto).toList(),
                books.getTotalElements(),
                books.getNumber(),
                books.getSize()

        );
    }

    @Transactional(readOnly = true)
    @Override
    public BookDto getByTitle(String title) {
        Book book = repository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        return mapper.toDto(book);
    }

    @Transactional(readOnly = true)
    @Override
    public BookDto getByIsbn(String isbn) {
        return repository.findByIsbn(isbn)
                .map(mapper::toDto)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

    }

    @Transactional(readOnly = true)
    @Override
    public PageDto<BookDto> getByAuthor(String author, Pageable pageable) {
        Page<Book> books = repository.findByAuthor(author, pageable);
        return new PageDto<BookDto>(
                books.getContent().stream().map(mapper::toDto).toList(),
                books.getTotalElements(),
                books.getNumber(),
                books.getSize()

        );

    }

    @Transactional(readOnly = true)
    @Override
    public PageDto<BookDto> getAvailableBooks(Pageable pageable) {
        Page<Book> books = repository.findByAvailableTrue(pageable);
        return new PageDto<BookDto>(
                books.getContent().stream().map(mapper::toDto).toList(),
                books.getTotalElements(),
                books.getNumber(),
                books.getSize()

        );

    }

    @Override
    public void deleteById(UUID bookId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Transactional
    @Override
    public BookDto save(BookRequest book, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BookNotFoundException("User not found"));
        Book bookEntity = mapper.toBook(book);
        bookEntity.setOwner(user);
        repository.save(bookEntity);
        BookDto dto = mapper.toDto(bookEntity);
        return dto;

    }

    @Transactional
    @Override
    public BookDto update(UUID bookId, BookRequest book, String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public PageDto<BookDto> getBooksByOwner(String username, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBooksByOwner'");
    }

    @Transactional
    @Override
    public String saveAll(List<BookRequest> books, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BookNotFoundException("User not found"));

        List<Book> bookEntities = books.stream()
                .map(bto -> {
                    Book bookEntity = mapper.toBook(bto);
                    bookEntity.setOwner(user);
                    return bookEntity;
                })
                .toList();
        repository.saveAll(bookEntities);
        return "Books saved successfully";

    }

    @Override
    public BookDto getBySlug(String slug) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBySlug'");
    }

}
