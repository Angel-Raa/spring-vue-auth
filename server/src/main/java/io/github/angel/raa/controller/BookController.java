package io.github.angel.raa.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.angel.raa.dto.PageDto;
import io.github.angel.raa.dto.request.book.BookRequest;
import io.github.angel.raa.dto.response.Response;
import io.github.angel.raa.dto.response.book.BookDto;
import io.github.angel.raa.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RequestMapping("/books")
@RestController
@RequiredArgsConstructor
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;

    @PreAuthorize("permitAll")
    @GetMapping
    public ResponseEntity<Response<PageDto<BookDto>>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        PageDto<BookDto> books = bookService.getAll(pageRequest);
        return ResponseEntity.ok(
                Response.<PageDto<BookDto>>success("Books retrieved successfully", books));
    }

    @PreAuthorize("permitAll")
    @GetMapping("/title/{title}")
    public ResponseEntity<Response<BookDto>> getByTitle(
            @PathVariable String title) {
        BookDto book = bookService.getByTitle(title);
        return ResponseEntity.ok(Response.<BookDto>success("Book retrieved successfully", book));

    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST', 'USER')")
    @PostMapping("/bulk")
    public ResponseEntity<Response<String>> saveAll(@RequestParam(name = "username") String username,
            @Valid @RequestBody List<BookRequest> books) {

        log.info("Username: {}", username);
        String message = bookService.saveAll(books, username);
        return ResponseEntity.ok(Response.<String>success("Books saved successfully", message));

    }
}
