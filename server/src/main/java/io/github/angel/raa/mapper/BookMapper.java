package io.github.angel.raa.mapper;

import org.springframework.stereotype.Component;

import io.github.angel.raa.dto.request.book.BookRequest;
import io.github.angel.raa.dto.response.book.BookDto;
import io.github.angel.raa.persistence.entity.Book;
import io.github.angel.raa.utils.Slugify;

@Component
public class BookMapper implements Mapper<BookDto, Book> {

    @Override
    public BookDto toDto(Book entity) {
        return new BookDto(
                entity.getBookId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getIsbn(),
                entity.getSlug(),
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
        book.setSlug(Slugify.slugify(dto.slug()));
        book.setPublicationYear(dto.publicationYear());
        book.setAvailable(dto.available());
        return book;

    }

    public BookRequest toRequest(Book book) {
        if (book == null) {
            return null;
        }
        return new BookRequest(
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublicationYear(),
                book.getAvailable());

    }

    public Book toBook(BookRequest book) {
        if (book == null) {
            return null;
        }

        Book bk = new Book();
        bk.setAuthor(book.getAuthor());
        bk.setIsbn(book.getIsbn());
        bk.setPublicationYear(book.getPublicationYear());
        bk.setAvailable(book.getAvailable());
        bk.setTitle(book.getTitle());
        bk.setSlug(Slugify.slugify(book.getTitle()));
        return bk;
    }
}
