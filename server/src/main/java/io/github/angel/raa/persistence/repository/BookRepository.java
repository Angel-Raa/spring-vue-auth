package io.github.angel.raa.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.angel.raa.persistence.entity.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {
    boolean existsByIsbn(String isbn);

    boolean existsByTitle(String title);

    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findByTitle(String title);

    Optional<Book> findByTitleContainingIgnoreCase(String title);

    Page<Book> findAll(Pageable pageable);

    Page<Book> findByAuthor(String author, Pageable pageable);

    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);

    Page<Book> findByAvailableTrue(Pageable pageable);

    // Listado de libro por cada user (owner)
    @Query(value = "SELECT b FROM Book b WHERE b.owner.username = :username", countQuery = "SELECT COUNT(b) FROM Book b WHERE b.owner.username = :username")
    Page<Book> findByOwnerUsername(String username, Pageable pageable);

}
