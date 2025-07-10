package io.github.angel.raa.service.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.angel.raa.dto.response.book.BookDto;
import io.github.angel.raa.mapper.BookMapper;
import io.github.angel.raa.persistence.repository.BookRepository;
import io.github.angel.raa.persistence.repository.UserRepository;
import io.github.angel.raa.service.BookService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final UserRepository userRepository;
    private final BookMapper mapper;
    


    @Override
    public Page<BookDto> getAll(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public BookDto getByTitle(String title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByTitle'");
    }

    @Override
    public BookDto getByIsbn(String isbn) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByIsbn'");
    }

    @Override
    public Page<BookDto> getByAuthor(String author, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByAuthor'");
    }

    @Override
    public Page<BookDto> getAvailableBooks(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableBooks'");
    }

    

    @Override
    public void deleteById(UUID bookId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public BookDto save(BookDto book, String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public BookDto update(UUID bookId, BookDto book, String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Page<BookDto> getBooksByOwner(String username, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBooksByOwner'");
    }

}
