package com.bookstore.project.web;

import com.bookstore.project.common.exceptions.RecordNotFoundException;
import com.bookstore.project.common.exceptions.RequestValidationException;
import com.bookstore.project.domain.Book;
import com.bookstore.project.domain.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
public class ApplicationController {

    private final Service service;

    @PostMapping("/books")
    public void addBook(@Valid @RequestBody Book request) throws Exception {
        service.addBook(request);
    }

    @PutMapping("/books/{isbn}")
    public void updateBook(@PathVariable String isbn, @Valid @RequestBody Book request) throws Exception {
        service.updateBook(isbn, request);
    }

    //TODO add restriction
    @DeleteMapping("/books/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        service.deleteBook(isbn);
    }

    @GetMapping("/books/{isbn}")
    public Book getBook(@PathVariable String isbn) throws Exception{
        return service.getBook(isbn);
    }

    @PostMapping("/books/getBooks")
    public List<Book> getBooks(@RequestBody GetBooksRequest request) {
        Set<Book> bookSet = new HashSet<>();

        if (request.getTitle() != null) {
            bookSet.addAll(service.getBooksByTitle(request.getTitle()));
        }
        if (request.getAuthor() != null) {
            bookSet.addAll(service.getBooksByAuthor(request.getAuthor()));
        }

        return new ArrayList<>(bookSet);
    }

    @ExceptionHandler({JsonProcessingException.class, JpaSystemException.class, MethodArgumentNotValidException.class, HttpMessageNotReadableException.class, RequestValidationException.class, HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidRequestException(
            Exception exception) {
        exception.printStackTrace();
        System.out.println("Known Exception: " + exception.getClass().getName());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid request");
    }

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleRecordNotFoundException(
            Exception exception) {
        exception.printStackTrace();
        System.out.println("Known Exception: " + exception.getClass().getName());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Record not found");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleUserNotFoundException(
            Exception exception) {
        exception.printStackTrace();
        System.out.println("Known Exception: " + exception.getClass().getName());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("User not found");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleAllException(
            Exception exception) {
        exception.printStackTrace();
        System.out.println("Unknown Exception: " + exception.getClass().getName());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unknown error occurred");
    }
}
