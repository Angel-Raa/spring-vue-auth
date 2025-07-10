package io.github.angel.raa.persistence.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "book_id")
    private UUID bookId;
    @Column(name = "title", nullable = false, unique = true, length = 100, columnDefinition = "VARCHAR(100)")
    private String title;
    @Column(name = "author", nullable = false, length = 100, columnDefinition = "VARCHAR(100)")
    private String author;
    @Column(name = "isbn", nullable = false, unique = true, length = 13, columnDefinition = "VARCHAR(13)")
    private String isbn;
    @Column(name = "publication_year", nullable = false, columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate publicationYear;
    @Column(name = "available", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean available;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
    private User owner;

}
