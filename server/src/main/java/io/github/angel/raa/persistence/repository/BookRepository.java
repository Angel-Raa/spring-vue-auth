package io.github.angel.raa.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import io.github.angel.raa.persistence.entity.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {
    boolean existsByIsbn(String isbn);

    boolean existsByTitle(String title);

    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findByTitle(String title);

    Optional<Book> findByTitleContainingIgnoreCase(String title);

    Page<Book> findAll(Pageable pageable);

    Page<Book> findAllByAuthor(String author, Pageable pageable);

    Page<Book> findAllByAuthorContainingIgnoreCase(String author, Pageable pageable);

    Page<Book> findAllByAvailableTrue(Pageable pageable);

}
