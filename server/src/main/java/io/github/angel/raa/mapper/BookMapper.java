package io.github.angel.raa.mapper;

import org.springframework.stereotype.Component;

import io.github.angel.raa.dto.response.book.BookDto;
import io.github.angel.raa.persistence.entity.Book;

@Component
public class BookMapper implements Mapper<BookDto, Book> {

    @Override
    public BookDto toDto(Book entity) {
        return new BookDto(
                entity.getBookId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getIsbn(),
                entity.getPublicationYear(),
                entity.getAvailable());

    }

    @Override
    public Book toEntity(BookDto dto) {
        Book book = new Book();
        book.setBookId(dto.bookId());
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setIsbn(dto.isbn());
        book.setPublicationYear(dto.publicationYear());
        book.setAvailable(dto.available());
        return book;

    }

}
